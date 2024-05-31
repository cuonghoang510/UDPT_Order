package vn.udpt.order.models.payment.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PaymentHandlerResponse {
    private String referenceId;
    private String paymentUrl;
    private String qrData;
}
