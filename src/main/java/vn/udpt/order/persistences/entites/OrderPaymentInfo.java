package vn.udpt.order.persistences.entites;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.udpt.order.models.enums.PaymentMethod;

@Document(collection = "orderPaymentInfo")
@Data
@Builder
public class OrderPaymentInfo {
    @Id
    private String id;
    private String orderId;
    private PaymentMethod paymentMethod;
    private double amount;
    private String qrData;
    private String paymentUrl;
}