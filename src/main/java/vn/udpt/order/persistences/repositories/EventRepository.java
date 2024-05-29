package vn.udpt.order.persistences.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.udpt.order.persistences.entites.Event;

public interface EventRepository extends JpaRepository<Event, String>{
}
