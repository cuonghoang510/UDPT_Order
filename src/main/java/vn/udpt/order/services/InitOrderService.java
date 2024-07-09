package vn.udpt.order.services;

import vn.udpt.order.models.order.request.GetPaymentUrlRequest;
import vn.udpt.order.models.order.request.InitOrderRequest;
import vn.udpt.order.models.order.request.InitOrderV2Request;
import vn.udpt.order.models.order.response.FetchPaymentMethodResponse;
import vn.udpt.order.models.order.response.InitOrderResponse;
import vn.udpt.order.models.order.response.InitOrderV2Response;

public interface InitOrderService {
    InitOrderResponse initOrder(InitOrderRequest request);
    InitOrderV2Response initOrderV2(InitOrderV2Request request);
    FetchPaymentMethodResponse fetchPaymentMethod(String orderId);
    String getPaymentUrl(GetPaymentUrlRequest request);

}
