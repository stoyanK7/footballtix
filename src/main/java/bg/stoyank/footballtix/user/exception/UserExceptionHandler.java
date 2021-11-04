package bg.stoyank.footballtix.user.exception;

import bg.stoyank.footballtix.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        HttpStatus status = UNPROCESSABLE_ENTITY;
        ApiException apiException = new ApiException(
                e.getMessage(),
                status,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, status);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e) {
        HttpStatus status = NOT_FOUND;
        ApiException apiException = new ApiException(
                e.getMessage(),
                status,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, status);
    }

    @ExceptionHandler(PasswordsDoNotMatchException.class)
    public ResponseEntity<Object> handlePasswordsDoNotMatchException(PasswordsDoNotMatchException e) {
        HttpStatus status = UNPROCESSABLE_ENTITY;
        ApiException apiException = new ApiException(
                e.getMessage(),
                status,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, status);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Object> handleInvalidCredentialsException(InvalidCredentialsException e) {
        HttpStatus status = UNAUTHORIZED;
        ApiException apiException = new ApiException(
                e.getMessage(),
                status,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        HttpStatus status = BAD_REQUEST;
        String message = "Invalid arguments: ";
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            message += (error.getField() + " -> " + error.getDefaultMessage() + "/");
        }

        for (ObjectError error : e.getBindingResult().getGlobalErrors()) {
            message += (error.getObjectName() + " -> " + error.getDefaultMessage() + "/");
        }
        ApiException apiException = new ApiException(
                message,
                status,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, status);
    }
}
