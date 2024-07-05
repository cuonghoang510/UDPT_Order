package vn.udpt.order.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.udpt.order.models.order.request.UpdateOrderStatusRequest;
import vn.udpt.order.models.order.response.UpdateOrderStatusResponse;
import vn.udpt.order.persistences.entites.Event;
import vn.udpt.order.persistences.entites.Order;
import vn.udpt.order.services.EmailService;
import vn.udpt.order.services.EventService;
import vn.udpt.order.services.OrderService;
import vn.udpt.order.services.WebhookService;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebhookServiceImpl implements WebhookService {

    private final OrderService orderService;
    private final EventService eventService;
    private final EmailService emailService;

    @Override
    public UpdateOrderStatusResponse updateOrderStatus(UpdateOrderStatusRequest request) {
        Order order = orderService.getById(request.getOrderId());

        order.setStatus(request.getOrderStatus());

        Event event = eventService.getById(order.getEventId());

        event.setStock(event.getStock() + order.getQuantity());

        eventService.save(event);

        orderService.save(order);

        emailService.sendEmail(order);

        return UpdateOrderStatusResponse.builder()
                .orderId(order.getId())
                .orderStatus(order.getStatus())
                .message("Update order status successfully")
                .build();
    }

}
