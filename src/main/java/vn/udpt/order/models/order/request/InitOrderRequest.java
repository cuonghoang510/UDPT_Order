package vn.udpt.order.models.order.request;

import lombok.*;
import vn.udpt.order.models.enums.Currency;
import vn.udpt.order.models.enums.PaymentMethod;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InitOrderRequest {
    private String userId;

    private String email;

    private String eventId;

    private String address;

    private PaymentMethod paymentMethod;

    private int quantity;

    private Currency currency;

    private String promotionId;

    private String callbackUrl;

    private String description;
}