package vn.udpt.order.factory.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.udpt.order.models.enums.PaymentMethod;
import vn.udpt.order.models.merchantProfile.response.MerchantProfileResponse;
import vn.udpt.order.models.momo.request.MomoOrderRequest;
import vn.udpt.order.models.payment.request.PaymentHandlerInput;
import vn.udpt.order.models.payment.response.PaymentHandlerResponse;
import vn.udpt.order.models.request.InitOrderRequest;
import vn.udpt.order.models.userProfile.response.UserProfileResponse;
import vn.udpt.order.persistences.entites.Event;

@Service
@Slf4j
@RequiredArgsConstructor
public class MomoProcessPayment extends BasePaymentProcess{

    @Override
    protected void validate(PaymentHandlerInput input) {

    }

    @Override
    protected PaymentHandlerResponse doPayment(PaymentHandlerInput input) {

        MomoOrderRequest momoOrderRequest = createMomoOrderRequest(input);

        // TODO: call momo service to create order

        return null;
    }

    @Override
    public boolean support(PaymentMethod paymentMethod) {
        return paymentMethod == PaymentMethod.MOMO;
    }

    private MomoOrderRequest createMomoOrderRequest(PaymentHandlerInput input) {
        return MomoOrderRequest.builder().build();
    }
}
