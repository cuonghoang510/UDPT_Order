package vn.udpt.order.models.merchantProfile.response;

import lombok.Data;

@Data
public class MerchantProfileResponse {
    private String id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String description;
    private String linkWebsite;
}