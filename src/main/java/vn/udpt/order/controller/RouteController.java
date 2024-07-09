package vn.udpt.order.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.udpt.order.models.dto.Response;
import vn.udpt.order.models.enums.Status;
import vn.udpt.order.models.order.request.GetPaymentUrlRequest;
import vn.udpt.order.models.order.request.InitOrderRequest;
import vn.udpt.order.models.order.request.InitOrderV2Request;
import vn.udpt.order.models.order.response.FetchPaymentMethodResponse;
import vn.udpt.order.models.order.response.InitOrderResponse;
import vn.udpt.order.models.order.response.InitOrderV2Response;
import vn.udpt.order.persistences.entites.Order;
import vn.udpt.order.persistences.entites.Route;
import vn.udpt.order.persistences.repositories.RouteRepository;
import vn.udpt.order.services.InitOrderService;
import vn.udpt.order.services.OrderService;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/route/api")
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")
public class RouteController {

    private final RouteRepository routeRepository;
    private final InitOrderService initOrderService;
    private final OrderService orderService;

    @GetMapping("/{routeId}")
    @SneakyThrows
    public ResponseEntity<Response<Object>> getRouteByRouteId(@PathVariable String routeId) {
        log.info("Init process get route by id for request {}", routeId);
        Optional<Route> response = routeRepository.findById(routeId);
        if(response.isEmpty()) {
            log.error("Route not found with id {}", routeId);
            return ResponseEntity.ok(new Response<>(Status.NOT_FOUND, Status.NOT_FOUND.getMessage(), null));
        }
        log.info("Done process get route by id for request {} with response {}", routeId, response);
        return ResponseEntity.ok(new Response<>(Status.SUCCESS, Status.SUCCESS.getMessage(), response));
    }

    @PostMapping("/place-order")
    public ResponseEntity<Response<Object>> placeOrder(@RequestBody @Valid InitOrderV2Request request) {
        log.info("Init process booking for request {}", request);
        InitOrderV2Response response = initOrderService.initOrderV2(request);
        log.info("Done process booking for request {} with response {}", request, response);
        return ResponseEntity.ok(new Response<>(Status.SUCCESS, Status.SUCCESS.getMessage(), response));
    }

    @GetMapping("/get-payment-method/{orderId}")
    public ResponseEntity<Response<Object>> placeOrder(@PathVariable  String orderId) {
        log.info("Init process get payment method for orderId: {}", orderId);
        FetchPaymentMethodResponse response = initOrderService.fetchPaymentMethod(orderId);
        log.info("Done process get payment method for orderId: {} with response {}", orderId, response);
        return ResponseEntity.ok(new Response<>(Status.SUCCESS, Status.SUCCESS.getMessage(), response));
    }

    @PostMapping("/get-payment-url")
    public ResponseEntity<Response<String>> getPaymentUrl(@RequestBody GetPaymentUrlRequest request) {
        log.info("Init process get payment url for id: {}", request);
        String response = initOrderService.getPaymentUrl(request);
        log.info("Done process get payment url for id: {} with response {}", request, response);
        return ResponseEntity.ok(new Response<>(Status.SUCCESS, Status.SUCCESS.getMessage(), response));
    }

    @GetMapping("/get-order-status/{orderId}")
    public ResponseEntity<Response<Object>> getPaymentUrl(@PathVariable String orderId) {
        log.info("Init process get payment url for id: {}", orderId);
        Order response = orderService.getById(orderId);
        log.info("Done process get payment url for id: {} with response {}", orderId, response);
        return ResponseEntity.ok(new Response<>(Status.SUCCESS, Status.SUCCESS.getMessage(), response));
    }

}
