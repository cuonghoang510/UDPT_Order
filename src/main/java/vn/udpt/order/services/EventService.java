package vn.udpt.order.services;

import vn.udpt.order.persistences.entites.Event;

public interface EventService {
    void save(Event event);
    Event getById(String id);
}
