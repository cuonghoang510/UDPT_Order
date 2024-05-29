package vn.udpt.order.services;

import vn.udpt.order.models.request.OrderInfoRequest;
import vn.udpt.order.models.response.ListOrderResponse;
import vn.udpt.order.models.response.OrderInfoResponse;
import vn.udpt.order.persistences.entites.Order;

public interface OrderService {
    Order save(Order order);
    OrderInfoResponse getOrderInfo(OrderInfoRequest request);
    ListOrderResponse getOrderByUserId(String userId);
    ListOrderResponse getOrderByMerchantId(String merchantId);
}
