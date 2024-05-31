package vn.udpt.order.models.fundiin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shipping {
    private String city;
    private String district;
    private String ward;
    private String street;
    private String houseNumber;
    private String country;
}
