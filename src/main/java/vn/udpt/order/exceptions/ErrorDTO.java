package vn.udpt.order.exceptions;

import lombok.Data;

@Data
public class ErrorDTO {
    private String status;
    private String message;

    public ErrorDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
