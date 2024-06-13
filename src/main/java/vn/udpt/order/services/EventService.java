package vn.udpt.order.services;

import vn.udpt.order.persistences.entites.Event;

import java.util.List;

public interface EventService {

    void save(Event event);
    Event getById(String id);
    List<Event> findByMerchantId(String merchantId);
}
