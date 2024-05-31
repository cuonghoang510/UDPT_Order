package vn.udpt.order.factory.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.udpt.order.models.dto.UserInfo;
import vn.udpt.order.models.enums.APIStatus;
import vn.udpt.order.models.enums.Currency;
import vn.udpt.order.models.enums.PaymentMethod;
import vn.udpt.order.models.exception.DefaultException;
import vn.udpt.order.models.fundiin.Amount;
import vn.udpt.order.models.fundiin.Shipping;
import vn.udpt.order.models.fundiin.request.FundiinOrderRequest;
import vn.udpt.order.models.fundiin.response.FundiinOrderResponse;
import vn.udpt.order.models.payment.request.PaymentHandlerInput;
import vn.udpt.order.models.payment.response.PaymentHandlerResponse;
import vn.udpt.order.services.HttpService;
import vn.udpt.order.utils.Constant;
import vn.udpt.order.utils.FundiinConstant;
import vn.udpt.order.utils.HeaderUtils;

@Component
@Slf4j
@RequiredArgsConstructor
public class FundiinProcessPayment extends BasePaymentProcess {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final HttpService httpService;

    @Value("${fundiin.client-id}")
    private String clientId;

    @Value("${fundiin.base-url}")
    private String fundiinBaseUrl;

    @Value("${fundiin.initiate-order-path}")
    private String initiateOrderPath = "/v2/payments";

    @Override
    protected void validate(PaymentHandlerInput input) {

    }

    @Override
    protected PaymentHandlerResponse doPayment(PaymentHandlerInput input) {

        FundiinOrderRequest fundiinOrderRequest = createFundiinOrderRequest(input);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set(Constant.CLIENT_ID,clientId);
        httpHeaders.set(Constant.SIGNATURE, HeaderUtils.createSignature(fundiinOrderRequest));
        HttpEntity<Object> entity = new HttpEntity<>(fundiinOrderRequest, httpHeaders);

        ResponseEntity<Object> responseEntity = httpService.sendPost(fundiinBaseUrl + initiateOrderPath,entity,restTemplate,Object.class);

        if(responseEntity.getBody() == null || responseEntity.getStatusCode() != HttpStatus.OK) {
            log.error("Error when calling Fundiin API: {}", responseEntity);
            throw new DefaultException(APIStatus.INITIATE_ORDER_FAILED);
        }

        FundiinOrderResponse fundiinOrderResponse = objectMapper.convertValue(responseEntity.getBody(), FundiinOrderResponse.class);

        return PaymentHandlerResponse.builder()
                .paymentUrl(fundiinOrderResponse.getPaymentUrl())
                .qrData(fundiinOrderResponse.getQrData())
                .build();
    }

    @Override
    public boolean support(PaymentMethod paymentMethod) {
        return paymentMethod == PaymentMethod.FUNDIIN;
    }

    private FundiinOrderRequest createFundiinOrderRequest(PaymentHandlerInput input) {
        return FundiinOrderRequest.builder()
                .merchantId(FundiinConstant.MERCHANTID)
                .referenceId(input.getOrderId())
                .requestType(FundiinConstant.REQUESTTYPE)
                .paymentMethod(FundiinConstant.PAYMENTMETHOD)
                .amount(Amount.builder().value(input.getAmount()).currency(Currency.VND.name()).build())
                .customer(UserInfo.builder().email(input.getEmail()).phoneNumber(input.getPhoneNumber()).firstName(input.getFullName()).lastName(input.getFullName()).build())
                .shipping(Shipping.builder().build()) // TODO: Update here
                .successRedirectUrl(FundiinConstant.SUCCESSREDIRECTURL)
                .unSuccessRedirectUrl(FundiinConstant.UNSUCCESSREDIRECTURL)
                .build();
    }
}