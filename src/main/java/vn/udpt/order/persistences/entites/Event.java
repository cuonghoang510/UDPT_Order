package vn.udpt.order.persistences.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.udpt.order.models.enums.EventStatus;
import vn.udpt.order.models.enums.EventType;

import java.time.Instant;

@Entity
@Table(name = "ticket")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Event {

    @Id
    private String id;

    @Column(name = "merchant_id")
    private String merchantId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private long price;

    @Column(name = "stock")
    private int stock;

    @Column(name = "min_quantity")
    private int minQuantity;

    @Column(name = "max_quantity")
    private int maxQuantity;

    @Column(name = "location")
    private String location;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private EventType type;

    public boolean isOutOfStock() {
        return stock == maxQuantity;
    }

    public boolean isUnAvailable() {
        return status != EventStatus.NOT_YET_STARTED || Instant.now().isAfter(startDate);
    }

}
