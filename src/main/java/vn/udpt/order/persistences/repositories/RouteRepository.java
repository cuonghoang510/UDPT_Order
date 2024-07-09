package vn.udpt.order.persistences.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.udpt.order.persistences.entites.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, String> {
}
