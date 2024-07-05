package vn.udpt.order.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vn.udpt.order.models.email.request.SendEmailRequest;
import vn.udpt.order.persistences.entites.Order;
import vn.udpt.order.services.EmailService;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final ObjectMapper objectMapper;
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void sendEmail(Order order) {
        restTemplate.postForEntity("http://localhost:8082/email/send/order-billing", order, Object.class);
    }
}
