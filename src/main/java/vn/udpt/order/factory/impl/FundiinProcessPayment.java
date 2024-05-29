package vn.udpt.order.factory.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.udpt.order.models.enums.PaymentMethod;
import vn.udpt.order.models.fundiin.request.FundiinOrderRequest;
import vn.udpt.order.models.payment.request.PaymentHandlerInput;
import vn.udpt.order.models.payment.response.PaymentHandlerResponse;

@Component
@Slf4j
@AllArgsConstructor
public class FundiinProcessPayment extends BasePaymentProcess {


    @Override
    protected void validate(PaymentHandlerInput input) {

    }

    @Override
    protected PaymentHandlerResponse doPayment(PaymentHandlerInput input) {

        FundiinOrderRequest fundiinOrderRequest = createFundiinOrderRequest(input);

        // TODO: call fundiin service to create order

        return null;

    }

    @Override
    public boolean support(PaymentMethod paymentMethod) {
        return paymentMethod == PaymentMethod.FUNDIIN;
    }

    private FundiinOrderRequest createFundiinOrderRequest(PaymentHandlerInput input) {
        return FundiinOrderRequest.builder().build();
    }
}