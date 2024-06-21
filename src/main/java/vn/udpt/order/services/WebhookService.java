package vn.udpt.order.services;

import vn.udpt.order.models.order.request.UpdateOrderStatusRequest;
import vn.udpt.order.models.order.response.UpdateOrderStatusResponse;

public interface WebhookService {
    UpdateOrderStatusResponse updateOrderStatus(UpdateOrderStatusRequest request);
}
