package vn.udpt.order.services;

import vn.udpt.order.models.event.request.CreateEventRequest;
import vn.udpt.order.models.event.response.CreateEventResponse;
import vn.udpt.order.models.event.response.GetListEventResponse;
import vn.udpt.order.persistences.entites.Event;

public interface EventManageService {
    CreateEventResponse createEvent(CreateEventRequest createEventRequest);
    GetListEventResponse getListEventByMerchantId(String merchantId);
    Event getEventById(String eventId);
}
