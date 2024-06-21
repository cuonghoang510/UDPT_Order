package vn.udpt.order.models.fundiin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FundiinItem {
    private String productId;
    private String productName;
    private long price;
    private String category;
    private Integer quantity;
    private String currency;
    private Integer totalAmount;
}
