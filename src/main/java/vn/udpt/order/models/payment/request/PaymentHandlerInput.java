package vn.udpt.order.models.payment.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.udpt.order.models.enums.PaymentMethod;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PaymentHandlerInput {
    private String orderId;
    private String merchantId;
    private String requestId;
    private long amount;
    private String currency;
    private String callbackUrl;
    private String description;
    private String phoneNumber;
    private String email;
    private String fullName;
    private PaymentMethod paymentMethod;
}