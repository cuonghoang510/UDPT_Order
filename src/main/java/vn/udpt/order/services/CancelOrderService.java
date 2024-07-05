package vn.udpt.order.services;

import vn.udpt.order.models.order.request.CancelOrderRequest;
import vn.udpt.order.models.order.response.CancelOrderResponse;

public interface CancelOrderService {
    CancelOrderResponse cancelOrder(CancelOrderRequest request);
}
