package vn.udpt.order.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.udpt.order.models.dto.Response;
import vn.udpt.order.models.enums.Status;
import vn.udpt.order.models.order.request.CancelOrderRequest;
import vn.udpt.order.models.order.request.OrderInfoRequest;
import vn.udpt.order.models.order.request.InitOrderRequest;
import vn.udpt.order.models.order.response.CancelOrderResponse;
import vn.udpt.order.models.order.response.ListOrderResponse;
import vn.udpt.order.models.order.response.OrderInfoResponse;
import vn.udpt.order.models.order.response.InitOrderResponse;
import vn.udpt.order.services.CancelOrderService;
import vn.udpt.order.services.InitOrderService;
import vn.udpt.order.services.OrderService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order/api")
@CrossOrigin(origins = "http://localhost:8000", allowedHeaders = "*")
public class OrderController {

    private final InitOrderService initOrderService;
    private final OrderService orderService;
    private final CancelOrderService cancelOrderService;

    @GetMapping("/get-detail/{orderId}")
    public ResponseEntity<Response<Object>> getOrderDetail(@PathVariable String orderId) {
        log.info("Init process get order status for request {}", orderId);
        OrderInfoResponse response = orderService.getOrderInfo(orderId);
        log.info("Done process get order status for request {} with response {}", orderId, response);
        return ResponseEntity.ok(new Response<>(Status.SUCCESS, Status.SUCCESS.getMessage(), response));
    }

    @PostMapping("/cancel-order")
    public ResponseEntity<Response<Object>> cancelOrder(@RequestBody CancelOrderRequest cancelOrderRequest) {
        log.info("Init process cancel order for request {}", cancelOrderRequest);
        CancelOrderResponse response = cancelOrderService.cancelOrder(cancelOrderRequest);
        log.info("Done process cancel order for request {} with response {}", cancelOrderRequest, response);
        return ResponseEntity.ok(new Response<>(Status.SUCCESS, Status.SUCCESS.getMessage(), response));
    }

    @GetMapping("/get-by-user/{userId}")
    public ResponseEntity<Response<Object>> getOrderByUserId(@PathVariable String userId) {
        log.info("Init process get order by user id {}", userId);
        ListOrderResponse response = orderService.getOrderByUserId(userId);
        log.info("Done process get order by user id {} with response {}", userId, response);
        return ResponseEntity.ok(new Response<>(Status.SUCCESS, Status.SUCCESS.getMessage(), response));
    }

    @PostMapping("/merchant/{merchantId}")
    public ResponseEntity<Response<Object>> getOrderByMerchantId(@PathVariable String merchantId) {
        log.info("Init process get order by merchant_id {}", merchantId);
        ListOrderResponse response = orderService.getOrderByMerchantId(merchantId);
        log.info("Done process get order by merchant_id {} with response {}", merchantId, response);
        return ResponseEntity.ok(new Response<>(Status.SUCCESS, Status.SUCCESS.getMessage(), response));
    }

    @PostMapping("/create")
    public ResponseEntity<Response<Object>> placeOrder(@RequestBody @Valid InitOrderRequest request) {
        log.info("Init process booking for request {}", request);
        InitOrderResponse response = initOrderService.initOrder(request);
        log.info("Done process booking for request {} with response {}", request, response);
        return ResponseEntity.ok(new Response<>(Status.SUCCESS, Status.SUCCESS.getMessage(), response));
    }
}
