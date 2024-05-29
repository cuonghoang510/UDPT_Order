package vn.udpt.order.models.momo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MomoOrderResponse {
    private String partnerCode;
    private String orderId;
    private String requestId;
    private int amount;
    private int resultCode;
    private String message;
    private long responseTime;
    private String payUrl;
    private String qrCodeUrl;
    private String deeplink;
    private String appLink;
}
