package vn.udpt.order.models.order.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CancelOrderResponse {
    private String orderId;
    private String message;
}
