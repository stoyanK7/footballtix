package bg.stoyank.footballtix.order.exception;

import bg.stoyank.footballtix.exception.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@ControllerAdvice
public class OrderExceptionHandler {
    @ExceptionHandler(OrderAlreadyExistsException.class)
    public ResponseEntity<Object> handleOrderAlreadyExistsException(OrderAlreadyExistsException e) {
        HttpStatus status = UNPROCESSABLE_ENTITY;
        ApiResponse apiResponse = new ApiResponse(
                e.getMessage(),
                status,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiResponse, status);
    }
}
