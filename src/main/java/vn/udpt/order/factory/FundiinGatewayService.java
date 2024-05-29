package vn.udpt.order.factory;

import vn.udpt.order.models.fundiin.request.FundiinOrderRequest;
import vn.udpt.order.models.fundiin.response.FundiinOrderResponse;
import vn.udpt.order.services.PaymentMethodExecute;

public interface FundiinGatewayService extends PaymentMethodExecute {
    FundiinOrderResponse placeFundiinOrder(FundiinOrderRequest initOrderRequest);
}