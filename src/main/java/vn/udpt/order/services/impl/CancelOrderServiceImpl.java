package vn.udpt.order.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.udpt.order.models.enums.APIStatus;
import vn.udpt.order.models.enums.OrderStatus;
import vn.udpt.order.models.exception.DefaultException;
import vn.udpt.order.models.order.request.CancelOrderRequest;
import vn.udpt.order.models.order.response.CancelOrderResponse;
import vn.udpt.order.persistences.entites.Event;
import vn.udpt.order.persistences.entites.Order;
import vn.udpt.order.services.CancelOrderService;
import vn.udpt.order.services.EventService;
import vn.udpt.order.services.OrderService;
import vn.udpt.order.services.UserService;

@Service
@Slf4j
@RequiredArgsConstructor
public class CancelOrderServiceImpl implements CancelOrderService {

    private final OrderService orderService;
    private final UserService userService;
    private final EventService eventService;

    @Override
    public CancelOrderResponse cancelOrder(CancelOrderRequest request) {

        Order order = orderService.getById(request.getOrderId());

        if (order == null) {
            throw new DefaultException(APIStatus.ORDER_NOT_FOUND);
        }

        if(OrderStatus.CREATED.equals(order.getStatus())) {
            order.setStatus(OrderStatus.CANCELED);
            Event event = eventService.getById(order.getEventId());
            event.setStock(event.getStock()-1);
            eventService.save(event);
            orderService.save(order);
            return new CancelOrderResponse(order.getId(), "success");
        }
        throw new DefaultException(APIStatus.CANCEL_FAIL);
    }
}
