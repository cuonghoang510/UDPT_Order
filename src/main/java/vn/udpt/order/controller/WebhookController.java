package vn.udpt.order.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.udpt.order.models.dto.Response;
import vn.udpt.order.models.order.request.UpdateOrderStatusRequest;
import vn.udpt.order.models.order.response.UpdateOrderStatusResponse;
import vn.udpt.order.services.WebhookService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order/api")
public class WebhookController {

    private final WebhookService webhookService;

    @PostMapping("/webhook")
    public ResponseEntity<Response<Object>> webhook(HttpServletRequest httpServletRequest, @RequestBody @Valid UpdateOrderStatusRequest request) {
        log.info("Init process webhook for request {}", request);
        UpdateOrderStatusResponse response = webhookService.updateOrderStatus(request);
        log.info("Process webhook successfully for request {} with response:{}", request,response);
        return ResponseEntity.ok(new Response<>(response));
    }

}
