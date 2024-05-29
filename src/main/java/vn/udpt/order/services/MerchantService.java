package vn.udpt.order.services;

import vn.udpt.order.models.merchantProfile.response.MerchantProfileResponse;

public interface MerchantService {
    MerchantProfileResponse getMerchantProfile(String merchantId);
}
