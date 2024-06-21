package vn.udpt.order.factory.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import vn.udpt.order.models.Item;
import vn.udpt.order.models.enums.Currency;
import vn.udpt.order.models.payment.request.PaymentHandlerInput;
import vn.udpt.order.models.payment.response.PaymentHandlerResponse;
import vn.udpt.order.models.payment.response.PaymentSubmissionResponse;
import vn.udpt.order.persistences.entites.Event;
import vn.udpt.order.persistences.entites.Order;

import java.util.UUID;

@RequiredArgsConstructor
public abstract class BasePaymentProcess implements PaymentProcessService {

    protected abstract void validate(PaymentHandlerInput input);

    protected abstract PaymentHandlerResponse doPayment(PaymentHandlerInput input);

    @Override
    public PaymentHandlerInput createPaymentHandlerInput(Order order) {
        return PaymentHandlerInput.builder()
                .orderId(order.getId())
                .merchantId(order.getMerchantId())
                .requestId(UUID.randomUUID().toString())
                .amount(order.getAmount())
                .currency(Currency.VND.name())
                .callbackUrl(order.getCallbackUrl())
                .description(order.getDescription())
                .phoneNumber(order.getPhoneNumber())
                .email(order.getEmail())
                .fullName(order.getFullName())
                .paymentMethod(order.getPaymentMethod())
                .item(Item.builder().productId(order.getEventId()).quantity(order.getQuantity()).price(order.getPrice()).build())
                .build();
    }

    @Override
    public PaymentSubmissionResponse processPayment(PaymentHandlerInput input) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        validate(input);
        PaymentHandlerResponse paymentResponse = doPayment(input);
        return modelMapper.map(paymentResponse, PaymentSubmissionResponse.class);
    }

}