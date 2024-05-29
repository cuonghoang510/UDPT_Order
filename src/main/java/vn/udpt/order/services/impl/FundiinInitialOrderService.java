package vn.udpt.order.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vn.udpt.order.models.fundiin.request.FundiinOrderRequest;
import vn.udpt.order.models.fundiin.response.FundiinOrderResponse;
import vn.udpt.order.factory.FundiinGatewayService;

@Service
@RequiredArgsConstructor
@Slf4j
public class FundiinInitialOrderService implements FundiinGatewayService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public FundiinOrderResponse placeFundiinOrder(FundiinOrderRequest initOrderRequest) {
        return null;
    }
}
