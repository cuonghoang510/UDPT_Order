package vn.udpt.order.models.momo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MomoOrderRequest {
    private String partnerCode;
    private String partnerName;
    private String requestId;
    private Long amount;
    private String orderId;
    private String orderInfo;
    private String redirectUrl;
    private String ipnUrl;
    private String partnerClientId;
    private String extraData;
    private String requestType;
    private String lang;
    private String signature;
    private String storeName;
    private int storeId;
}
