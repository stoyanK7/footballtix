package bg.stoyank.footballtix.registration.confirmationtoken.exception;

import bg.stoyank.footballtix.exception.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ConfirmationTokenExceptionHandler {
    @ExceptionHandler(ConfirmationTokenExpiredException.class)
    public ResponseEntity<Object> handleConfirmationTokenExpiredException(ConfirmationTokenExpiredException e) {
        HttpStatus status = BAD_REQUEST;
        ApiResponse apiResponse = new ApiResponse(
                "Confirmation token has expired. Attempt to log in again to receive another one.",
                status,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiResponse, status);
    }

    @ExceptionHandler(ConfirmationTokenNotFoundException.class)
    public ResponseEntity<Object> handleConfirmationTokenNotFoundException(ConfirmationTokenNotFoundException e) {
        HttpStatus status = NOT_FOUND;
        ApiResponse apiResponse = new ApiResponse(
                "Confirmation token not found. Attempt to log in again to receive another one.",
                status,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiResponse, status);
    }
}
