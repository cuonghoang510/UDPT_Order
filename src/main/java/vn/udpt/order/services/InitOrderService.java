package vn.udpt.order.services;

import vn.udpt.order.models.request.InitOrderRequest;
import vn.udpt.order.models.response.InitOrderResponse;

public interface InitOrderService {
    InitOrderResponse initOrder(InitOrderRequest request);
}
