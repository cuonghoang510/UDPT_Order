package vn.udpt.order.models.fundiin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Amount {
    private String currency;
    private long value;
}
