package vn.udpt.order.factory.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vn.udpt.order.models.enums.APIStatus;
import vn.udpt.order.models.enums.PaymentMethod;
import vn.udpt.order.models.exception.DefaultException;
import vn.udpt.order.models.momo.SubscriptionInfo;
import vn.udpt.order.models.momo.request.MomoOrderRequest;
import vn.udpt.order.models.momo.response.MomoOrderResponse;
import vn.udpt.order.models.payment.request.PaymentHandlerInput;
import vn.udpt.order.models.payment.response.PaymentHandlerResponse;
import vn.udpt.order.services.HttpService;
import vn.udpt.order.utils.MomoConstant;
import vn.udpt.order.utils.SignatureGenerator;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class MomoProcessPayment extends BasePaymentProcess{

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private final HttpService httpService;

    @Override
    protected void validate(PaymentHandlerInput input) {

    }

    @Override
    @SneakyThrows
    protected PaymentHandlerResponse doPayment(PaymentHandlerInput input) {

        MomoOrderRequest momoInitPaymentRequest = createMomoOrderRequest(input);

        momoInitPaymentRequest.setSignature(SignatureGenerator.generateHmacSHA256Signature(MomoConstant.SECRET_KEY,SignatureGenerator.momoRequestData(momoInitPaymentRequest)));

        MomoOrderResponse momoInitPaymentResponse = initPayment(momoInitPaymentRequest);

        return PaymentHandlerResponse.builder()
                .paymentUrl(momoInitPaymentResponse.getPayUrl())
                .qrData(momoInitPaymentResponse.getQrCodeUrl())
                .referenceId(momoInitPaymentResponse.getOrderId())
                .build();
    }

    @Override
    public boolean support(PaymentMethod paymentMethod) {
        return paymentMethod == PaymentMethod.MOMO;
    }

    public MomoOrderResponse initPayment(MomoOrderRequest request) {

        HttpEntity<MomoOrderRequest> requestHttpEntity = new HttpEntity<>(request);

        ResponseEntity<Object> responseEntity = restTemplate.exchange("https://test-payment.momo.vn" + "/v2/gateway/api/create", HttpMethod.POST, requestHttpEntity, Object.class, new Object[0]);
        log.info("Momo order response: {}", responseEntity);
        if(responseEntity.getBody() == null || responseEntity.getStatusCode() != HttpStatus.OK) {
            log.error("Error when calling Fundiin API: {}", responseEntity);
            throw new DefaultException(APIStatus.INITIATE_ORDER_FAILED);
        }

        MomoOrderResponse momoOrderResponse = objectMapper.convertValue(responseEntity.getBody(), MomoOrderResponse.class);
        log.info("Fundiin order response: {}", momoOrderResponse);

        return momoOrderResponse;
    }

    private MomoOrderRequest createMomoOrderRequest(PaymentHandlerInput input) {
        return MomoOrderRequest.builder()
                .partnerCode(MomoConstant.PARTNER_CODE)
                .requestId(UUID.randomUUID().toString())
                .amount(input.getAmount())
                .orderId(input.getOrderId())
                .storeId(10)
                .partnerName("Cuong dep trai")
                .storeName("Cuong dep trai")
                .orderInfo("Guitar Merchant create an payment request")
                .redirectUrl("http://localhost:3000")
                .ipnUrl("http://localhost:8080/order/api/update")
                .extraData("")
                .requestType("captureWallet")
                .subscriptionInfo(createSubscriptionInfo())
                .lang("vi")
                .build();
    }

    private SubscriptionInfo createSubscriptionInfo() {
        return SubscriptionInfo.builder()
                .partnerSubsId("SubId-1645170503079")
                .name("Goi ABC Premium 1645170503079")
                .type("VARIABLE")
                .frequency("MONTHLY")
                .subsOwner("Owner A")
                .recurringAmount(60000L)
                .build();
    }

}
