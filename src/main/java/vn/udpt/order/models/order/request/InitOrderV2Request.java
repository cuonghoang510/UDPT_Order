package vn.udpt.order.models.order.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InitOrderV2Request {
    private String phoneNumber;

    private String fullName;

    private String email;

    private String startLocation;

    private String destinationLocation;

    private int quantity;

    private String routeId;
}
