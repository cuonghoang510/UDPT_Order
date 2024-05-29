package vn.udpt.order.models.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.udpt.order.models.enums.APIStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class DefaultException extends RuntimeException {
    private String status;
    private String message;

    public DefaultException(APIStatus apiStatus) {
        super(apiStatus.getMessage());
        this.status = apiStatus.getStatus();
        this.message = apiStatus.getMessage();
    }

}
