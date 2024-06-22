package vn.udpt.order.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.udpt.order.models.enums.APIStatus;
import vn.udpt.order.models.exception.DefaultException;
import vn.udpt.order.persistences.entites.Event;
import vn.udpt.order.persistences.repositories.EventRepository;
import vn.udpt.order.services.EventService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public void save(Event event) {
        eventRepository.save(event);
    }

    @Override
    public Event getById(String id) {
        return eventRepository.findById(id).orElseThrow(() -> new DefaultException(APIStatus.EVENT_NOT_FOUND));
    }

    @Override
    public List<Event> findByMerchantId(String merchantId) {
        return eventRepository.findByMerchantId(merchantId).orElseThrow( () -> new DefaultException(APIStatus.EVENT_NOT_FOUND));
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

}