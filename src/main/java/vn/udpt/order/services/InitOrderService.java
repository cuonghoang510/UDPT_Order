package vn.udpt.order.services;

import vn.udpt.order.models.order.request.InitOrderRequest;
import vn.udpt.order.models.order.response.InitOrderResponse;

public interface InitOrderService {
    InitOrderResponse initOrder(InitOrderRequest request);
}
