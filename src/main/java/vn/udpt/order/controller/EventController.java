package vn.udpt.order.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.udpt.order.models.dto.Response;
import vn.udpt.order.models.enums.Status;
import vn.udpt.order.models.event.request.CreateEventRequest;
import vn.udpt.order.models.event.response.CreateEventResponse;
import vn.udpt.order.models.event.response.GetListEventResponse;
import vn.udpt.order.persistences.entites.Event;
import vn.udpt.order.services.EventManageService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/event/api")
public class EventController {

    private final EventManageService eventManageService;

    @GetMapping("/test")
    public ResponseEntity<Response<Object>> test() {
        return ResponseEntity.ok(new Response<>(Status.SUCCESS, Status.SUCCESS.getMessage(), "Hello"));
    }

    @PostMapping("/create")
    public ResponseEntity<Response<Object>> createEvent(@RequestBody @Valid CreateEventRequest request) {
        log.info("Init process create event for request {}", request);
        CreateEventResponse response = eventManageService.createEvent(request);
        log.info("Done process create event for request {} with response {}", request, response);
        return ResponseEntity.ok(new Response<>(Status.SUCCESS, Status.SUCCESS.getMessage(), response));
    }

    @GetMapping("/get-by-merchant/{merchantId}")
    public ResponseEntity<Response<Object>> getListEventByMerchantId(@PathVariable String merchantId) {
        log.info("Init process get list event for request {}", merchantId);
        GetListEventResponse response = eventManageService.getListEventByMerchantId(merchantId);
        log.info("Done process get list event for merchantId {} with response {}", merchantId, response);
        return ResponseEntity.ok(new Response<>(Status.SUCCESS, Status.SUCCESS.getMessage(), response));
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Response<Object>> getEventByEventId(@PathVariable String eventId) {
        log.info("Init process get event by id for request {}", eventId);
        Event response = eventManageService.getEventById(eventId);
        log.info("Done process get event by eventId with response {}", eventId, response);
        return ResponseEntity.ok(new Response<>(Status.SUCCESS, Status.SUCCESS.getMessage(), response));
    }

}
