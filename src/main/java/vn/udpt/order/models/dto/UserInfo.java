package vn.udpt.order.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserInfo {
    private String phoneNumber;
    private String email;
    private String firstName;
    private String lastName;
}
