package vn.udpt.order.models.fundiin.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.udpt.order.models.dto.UserInfo;
import vn.udpt.order.models.fundiin.Amount;
import vn.udpt.order.models.fundiin.FundiinItem;
import vn.udpt.order.models.fundiin.Shipping;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FundiinOrderRequest {
    private String merchantId;
    private String referenceId;
    private String requestType;
    private String paymentMethod;
    private String description;
    private String successRedirectUrl;
    private String unSuccessRedirectUrl;
    private Amount amount;
    private UserInfo customer;
    private List<FundiinItem> items;
    private Shipping shipping;
}
