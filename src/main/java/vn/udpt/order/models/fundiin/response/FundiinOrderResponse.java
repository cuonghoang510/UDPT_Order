package vn.udpt.order.models.fundiin.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundiinOrderResponse {
    private String merchantId;
    private String referenceId;
    private String responseTime;
    private String resultStatus;
    private String resultMsg;
    private String paymentUrl;
    private String qrData;
}
