package vn.udpt.order.factory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.udpt.order.factory.impl.PaymentProcessService;
import vn.udpt.order.models.enums.APIStatus;
import vn.udpt.order.models.enums.PaymentMethod;
import vn.udpt.order.models.exception.DefaultException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentProcessFactory {

    private final List<PaymentProcessService> services;

    public PaymentProcessService getPaymentProcess(PaymentMethod paymentMethod) {
        return services.stream()
                .filter(s -> s.support(paymentMethod))
                .findFirst()
                .orElseThrow(() -> new DefaultException(APIStatus.PAYMENT_NOT_SUPPORT));
    }

}
