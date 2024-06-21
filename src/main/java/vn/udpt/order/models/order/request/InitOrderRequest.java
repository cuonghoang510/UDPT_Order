package vn.udpt.order.models.order.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import vn.udpt.order.models.enums.Currency;
import vn.udpt.order.models.enums.PaymentMethod;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InitOrderRequest {
//    @NotBlank
    private String userId;

//    @NotBlank
    private String email;

//    @NotBlank
    private String eventId;

//    @NotBlank
    private String address;

//    @NotNull
    private PaymentMethod paymentMethod;

//    @Min(1)
    private int quantity;

//    @NotNull
    private Currency currency;

    private String promotionId;

    private String callbackUrl;

    private String description;
}