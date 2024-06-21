package vn.udpt.order.models.order.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.udpt.order.models.enums.OrderStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderStatusRequest {
    private String orderId;
    private OrderStatus orderStatus;
}