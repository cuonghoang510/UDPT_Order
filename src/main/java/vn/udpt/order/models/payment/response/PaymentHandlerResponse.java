package vn.udpt.order.models.payment.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentHandlerResponse {
    private String referenceId;
    private String paymentUrl;
    private String qrData;
}
