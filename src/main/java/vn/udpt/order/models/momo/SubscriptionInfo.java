package vn.udpt.order.models.momo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionInfo {
    private String name;
    private String partnerSubsId;
    private String subsOwner;
    private String type;
    private Long recurringAmount;
    private String frequency;
}