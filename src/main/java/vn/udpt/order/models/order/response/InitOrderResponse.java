package vn.udpt.order.models.order.response;

import lombok.*;
import vn.udpt.order.models.Item;
import vn.udpt.order.models.enums.OrderStatus;
import vn.udpt.order.models.enums.PaymentMethod;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InitOrderResponse {
    private String orderId;
    private String fullName;
    private String phoneNumber;
    private String email;
    private List<Item> productList;
    private PaymentMethod paymentMethod;
    private OrderStatus status;
    private Instant createdAt;
    private String paymentUrl;
}