package vn.udpt.order.persistences.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.udpt.order.models.enums.OrderStatus;
import vn.udpt.order.models.enums.PaymentMethod;

import java.time.Instant;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Order {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "merchant_id")
    private String merchantId;

    @Column(name = "event_id")
    private String eventId;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "event_address")
    private String eventAddress;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "amount")
    private long amount;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "expired_date")
    private Instant expiredDate;

    @Column(name = "start_event_date")
    private Instant startEventDate;

    @Column(name = "promotion_id")
    private String promotionId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private long price;

    @Column(name = "callback_url")
    private String callbackUrl;

    @Column(name = "description")
    private String description;

}
