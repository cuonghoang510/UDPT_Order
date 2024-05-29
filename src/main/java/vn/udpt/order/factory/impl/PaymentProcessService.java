package vn.udpt.order.factory.impl;

import vn.udpt.order.models.enums.PaymentMethod;
import vn.udpt.order.models.payment.request.PaymentHandlerInput;
import vn.udpt.order.models.payment.response.PaymentSubmissionResponse;
import vn.udpt.order.persistences.entites.Order;

public interface PaymentProcessService {
    boolean support(PaymentMethod paymentMethod);
    PaymentHandlerInput createPaymentHandlerInput(Order order);
    PaymentSubmissionResponse processPayment(PaymentHandlerInput input);
}
