package vn.udpt.order.models.payment.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentSubmissionResponse {
    private String paymentUrl;
    private String qrData;
}
