package vn.udpt.order.services;

import vn.udpt.order.models.event.request.CreateEventRequest;
import vn.udpt.order.models.event.response.CreateEventResponse;
import vn.udpt.order.models.event.response.GetListEventResponse;

public interface EventManageService {
    CreateEventResponse createEvent(CreateEventRequest createEventRequest);
    GetListEventResponse getListEventByMerchantId(String merchantId);
}
