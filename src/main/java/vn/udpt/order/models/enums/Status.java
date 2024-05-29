package vn.udpt.order.models.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.http.HttpStatus;

public enum Status {
    /**
     * Success status.
     */
    SUCCESS("SUCCESS", HttpStatus.OK, "response.success"),
    /**
     * Bad request status.
     */
    BAD_REQUEST("BAD_REQUEST", HttpStatus.BAD_REQUEST, "response.bad.request"),
    /**
     * Fail status.
     */
    FAIL("FAIL", HttpStatus.OK, "response.fail"),

    /**
     * Token expired status.
     */
    TOKEN_EXPIRED("TOKEN_EXPIRED", HttpStatus.UNAUTHORIZED, "response.token.expired"),
    /**
     * Token invalid status.
     */
    TOKEN_INVALID("TOKEN_INVALID",  HttpStatus.UNAUTHORIZED, "response.token.invalid"),
    /**
     * Unauthorized status.
     */
    UNAUTHORIZED("UNAUTHORIZED", HttpStatus.UNAUTHORIZED, "response.unauthorized"),
    /**
     * Time out status.
     */
    TIME_OUT("TIME_OUT", HttpStatus.REQUEST_TIMEOUT, "response.time.out"),
    /**
     * Exist status.
     */
    EXIST("EXIST", HttpStatus.OK, "response.exist"),
    /**
     * System error status.
     */
    SYSTEM_ERROR("SYSTEM_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, "response.system.error"),
    /**
     * Not found status.
     */
    NOT_FOUND("NOT_FOUND", HttpStatus.NOT_FOUND, "response.not.found"),

    /**
     * Bussiness errpr status.
     */
    BUSINESS_ERROR("BUSINESS_ERROR",  HttpStatus.OK, "response.business.error"),

    /**
     * Be errpr status.
     */
    BE_COMMUNICATION_ERROR("BE_COMMUNICATION_ERROR",  HttpStatus.OK, "response.be.error");


    private final String statusCode;
    private final HttpStatus httpStatus;

    private final String message;



    Status(String statusCode, HttpStatus httpStatus, String message) {
        this.statusCode = statusCode;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    /**
     * Gets status code.
     *
     * @return the status code
     */
    public String getMessage() {
        return message;
    }


    /**
     * Gets status code.
     *
     * @return the status code
     */

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }


    /**
     * Gets status code.
     *
     * @return the status code
     */

    @JsonValue
    public String getStatusCode() {
        return statusCode;
    }
}
