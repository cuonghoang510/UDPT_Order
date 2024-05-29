package vn.udpt.order.exceptions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vn.udpt.order.models.exception.DefaultException;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DefaultException.class)
    public ResponseEntity<ErrorDTO> handleDefaultException(DefaultException ex) {
        log.info("Handling DefaultException: {}", ex.getMessage());
        return ResponseEntity.ok(new ErrorDTO(ex.getStatus(),ex.getMessage()));
    }

}
