package vn.udpt.order.models.order.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.udpt.order.models.enums.Currency;
import vn.udpt.order.models.enums.OrderStatus;
import vn.udpt.order.models.enums.PaymentMethod;
import vn.udpt.order.persistences.entites.Event;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderInfoResponse {
    private String id;
    private String userId;
    private String phoneNumber;
    private String address;
    private PaymentMethod paymentMethod;
    private int amount;
    private Currency currency;
    private OrderStatus status;
    private Date createdDate;
    private Date expiredDate;
    private List<Event> events;
}
