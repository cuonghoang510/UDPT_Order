package vn.udpt.order.models.order.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.udpt.order.models.enums.OrderStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateOrderStatusResponse {
    private String orderId;
    private OrderStatus orderStatus;
    private String message;
}
