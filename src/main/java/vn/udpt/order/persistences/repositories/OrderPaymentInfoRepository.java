package vn.udpt.order.persistences.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.udpt.order.models.enums.PaymentMethod;
import vn.udpt.order.persistences.entites.OrderPaymentInfo;

import java.util.Optional;

public interface OrderPaymentInfoRepository extends MongoRepository<OrderPaymentInfo,String> {
    Optional<OrderPaymentInfo> findByOrderIdAndPaymentMethod(String orderId, PaymentMethod paymentMethod);
}
