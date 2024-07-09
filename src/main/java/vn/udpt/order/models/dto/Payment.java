package vn.udpt.order.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    private String paymentMethodId;
    private String paymentMethodName;
    private String orderId;
}
