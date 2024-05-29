package vn.udpt.order.models.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import vn.udpt.order.models.enums.Currency;
import vn.udpt.order.models.enums.PaymentMethod;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InitOrderRequest {
    @NotBlank
    private String userId;

    @NotBlank
    private String email;

    @NotBlank
    private String eventId;

    @NotBlank
    private String address;

    @NotBlank
    private PaymentMethod paymentMethod;

    @NotBlank
    private int quantity;

    @NotBlank
    private Currency currency;

    private String promotionId;

    private String callbackUrl;

    private String description;
}