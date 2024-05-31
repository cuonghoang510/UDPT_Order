package vn.udpt.order.persistences.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.udpt.order.persistences.entites.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, String>{
    Optional<List<Event>> findByMerchantId(String merchantId);
}
