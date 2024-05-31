package vn.udpt.order.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.udpt.order.models.enums.APIStatus;
import vn.udpt.order.models.exception.DefaultException;
import vn.udpt.order.models.order.request.OrderInfoRequest;
import vn.udpt.order.models.order.response.ListOrderResponse;
import vn.udpt.order.models.order.response.OrderInfoResponse;
import vn.udpt.order.persistences.entites.Order;
import vn.udpt.order.persistences.repositories.OrderRepository;
import vn.udpt.order.services.OrderService;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    @SneakyThrows
    public OrderInfoResponse getOrderInfo(OrderInfoRequest request) {
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new DefaultException(APIStatus.ORDER_NOT_FOUND));
        return objectMapper.convertValue(order, OrderInfoResponse.class);
    }

    @Override
    @SneakyThrows
    public ListOrderResponse getOrderByUserId(String userId) {
        return new ListOrderResponse(orderRepository.getByUserId(userId)
                .orElseThrow(() -> new DefaultException(APIStatus.GET_LIST_ORDER_FAIL)));
    }

    @Override
    public ListOrderResponse getOrderByMerchantId(String merchantId) {
        return new ListOrderResponse(orderRepository.getByMerchantId(merchantId)
                .orElseThrow(() -> new DefaultException(APIStatus.GET_LIST_ORDER_FAIL)));
    }

}
