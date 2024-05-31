package vn.udpt.order.models.order.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.udpt.order.persistences.entites.Order;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListOrderResponse {
    private List<Order> orders;
}
