package vn.udpt.order.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.udpt.order.models.enums.APIStatus;
import vn.udpt.order.models.enums.EventStatus;
import vn.udpt.order.models.event.request.CreateEventRequest;
import vn.udpt.order.models.event.response.CreateEventResponse;
import vn.udpt.order.models.event.response.GetListEventResponse;
import vn.udpt.order.models.exception.DefaultException;
import vn.udpt.order.models.merchantProfile.response.MerchantProfileResponse;
import vn.udpt.order.persistences.entites.Event;
import vn.udpt.order.services.EventManageService;
import vn.udpt.order.services.EventService;
import vn.udpt.order.services.MerchantService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventManageServiceImpl implements EventManageService {

    private final EventService eventService;
    private final MerchantService merchantService;

    @Override
    @SneakyThrows
    public CreateEventResponse createEvent(CreateEventRequest createEventRequest) {

        MerchantProfileResponse merchantProfileResponse = merchantService.getMerchantProfile(createEventRequest.getMerchantId());

        if (merchantProfileResponse == null) {
            log.error("Merchant not found in create event flow");
            throw new DefaultException(APIStatus.MERCHANT_NOT_FOUND);
        }

        Event event = Event.builder()
                .id(UUID.randomUUID().toString())
                .merchantId(merchantProfileResponse.getId())
                .title(createEventRequest.getTitle())
                .description(createEventRequest.getDescription())
                .startDate(createEventRequest.getStartDate())
                .endDate(createEventRequest.getEndDate())
                .location(createEventRequest.getLocation())
                .status(EventStatus.NOT_YET_STARTED)
                .type(createEventRequest.getType())
                .maxQuantity(createEventRequest.getMaxQuantity())
                .minQuantity(createEventRequest.getMinQuantity())
                .price(createEventRequest.getPrice())
                .stock(0)
                .build();

        eventService.save(event);

        return CreateEventResponse.builder().id(event.getId()).result("success").build();
    }

    @Override
    public GetListEventResponse getListEvent(String merchantId) {
        MerchantProfileResponse merchantProfileResponse = merchantService.getMerchantProfile(merchantId);

        if (merchantProfileResponse == null) {
            log.error("Merchant not found in get list event flow");
            throw new DefaultException(APIStatus.MERCHANT_NOT_FOUND);
        }

        List<Event> events = eventService.findByMerchantId(merchantId);

        return GetListEventResponse.builder().events(events).build();
    }
}
