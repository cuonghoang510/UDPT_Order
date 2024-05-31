package vn.udpt.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.udpt.order.models.dto.Response;
import vn.udpt.order.models.enums.Status;
import vn.udpt.order.models.event.request.CreateEventRequest;
import vn.udpt.order.models.event.response.CreateEventResponse;
import vn.udpt.order.models.event.response.GetListEventResponse;
import vn.udpt.order.services.EventManageService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/event/api")
public class EventController {

    private final EventManageService eventManageService;

    @PostMapping("/create")
    public ResponseEntity<Response<Object>> createEvent(@RequestBody CreateEventRequest request) {
        log.info("Init process create event for request {}", request);
        CreateEventResponse response = eventManageService.createEvent(request);
        log.info("Done process create event for request {} with response {}", request, response);
        return ResponseEntity.ok(new Response<>(Status.SUCCESS, Status.SUCCESS.getMessage(), response));
    }

    @GetMapping("/{merchantId}")
    public ResponseEntity<Response<Object>> getListEventByMerchantId(@PathVariable(required = true) String merchantId) {
        log.info("Init process get list event for request {}", merchantId);
        GetListEventResponse response = eventManageService.getListEvent(merchantId);
        log.info("Done process get list event for merchantId {} with response {}", merchantId, response);
        return ResponseEntity.ok(new Response<>(Status.SUCCESS, Status.SUCCESS.getMessage(), response));
    }

}
