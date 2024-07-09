package vn.udpt.order.models.enums;

import lombok.Getter;

@Getter
public enum APIStatus {
    // ORDER API ERROR
    GET_LIST_ORDER_FAIL("GET_LIST_ORDER_FAIL", "Get list order fail"),
    ORDER_NOT_FOUND("ORDER_NOT_FOUND", "Order not found"),

    // USER API ERROR
    USER_INVALID("USER_INVALID", "User invalid"),
    USER_NOT_FOUND("USER_NOT_FOUND", "User not found"),

    // EVENT API ERROR
    EVENT_NOT_FOUND("EVENT_NOT_FOUND", "Event not found"),
    EVENT_OUT_OF_STOCK("EVENT_OUT_OF_STOCK", "Event is out of stock"),
    EVENT_INVALID("EVENT_INVALID", "Event is unavailable because of status invalid or expired for event"),

    // MERCHANT API ERROR
    MERCHANT_NOT_FOUND("MERCHANT_NOT_FOUND", "Merchant not found"),

    // PAYMENT API ERROR
    PAYMENT_NOT_SUPPORT("PAYMENT_NOT_SUPPORT", "Payment method not support"),

    // Gateway Error
    INITIATE_ORDER_FAILED("INITIATE_ORDER_FAILED", "Initiate order failed"),
    CANCEL_FAIL("CANCEL_FAIL", "Cancel order failed"),
    ROUTE_NOT_FOUND("ROUTE_NOT_FOUND", "route not found");

    private final String status;
    private final String message;

    APIStatus(String status, String msg) {
        this.status = status;
        this.message = msg;
    }

}
