package vn.udpt.order.persistences.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.udpt.order.persistences.entites.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, String>{
    Optional<List<Order>> getByUserId(String userId);
    Optional<List<Order>> getByMerchantId(String merchantId);
}
