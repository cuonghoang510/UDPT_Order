package vn.udpt.order.services;

import vn.udpt.order.persistences.entites.Order;

public interface EmailService {
    void sendEmail(Order order);
}
