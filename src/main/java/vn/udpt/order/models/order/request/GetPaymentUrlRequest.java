package vn.udpt.order.models.order.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.udpt.order.models.enums.PaymentMethod;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPaymentUrlRequest {
    private String orderId;
    private PaymentMethod paymentMethod;
}
