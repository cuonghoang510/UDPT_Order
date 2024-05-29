package vn.udpt.order.services;

import vn.udpt.order.models.userProfile.response.UserProfileResponse;

public interface UserService {
    UserProfileResponse getUserProfile(String userId);
}
