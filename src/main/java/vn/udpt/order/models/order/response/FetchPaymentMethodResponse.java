package vn.udpt.order.models.order.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.udpt.order.models.dto.Payment;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FetchPaymentMethodResponse {

    private String orderId;
    private String status;

    private String fullName;
    private String phoneNumber;
    private String email;

    private String routeName;
    private String startLocation;
    private String destinationLocation;

    private long totalAmount;

    private List<Payment> payments;

    private String paymentMethodId;
}
