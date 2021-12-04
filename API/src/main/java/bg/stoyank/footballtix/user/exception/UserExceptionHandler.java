package bg.stoyank.footballtix.user.exception;

import bg.stoyank.footballtix.exception.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
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
    @ExceptionHandler(EmailAlreadyTakenException.class)
    public ResponseEntity<Object> handleUserAlreadyExistsException(EmailAlreadyTakenException e) {
        HttpStatus status = UNPROCESSABLE_ENTITY;
        ApiResponse apiResponse = new ApiResponse(
                e.getMessage(),
                status,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiResponse, status);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e) {
        HttpStatus status = NOT_FOUND;
        ApiResponse apiResponse = new ApiResponse(
                e.getMessage(),
                status,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiResponse, status);
    }

    @ExceptionHandler(PasswordsDoNotMatchException.class)
    public ResponseEntity<Object> handlePasswordsDoNotMatchException(PasswordsDoNotMatchException e) {
        HttpStatus status = UNPROCESSABLE_ENTITY;
        ApiResponse apiResponse = new ApiResponse(
                e.getMessage(),
                status,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiResponse, status);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Object> handleInvalidCredentialsException(InvalidCredentialsException e) {
        HttpStatus status = UNAUTHORIZED;
        ApiResponse apiResponse = new ApiResponse(
                e.getMessage(),
                status,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiResponse, status);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<Object> handleDisabledException(DisabledException e) {
        HttpStatus status = LOCKED;
        ApiResponse apiResponse = new ApiResponse(
                "Your account is not enabled. Do you want to receive a confirmation email?",
                status,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiResponse, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        HttpStatus status = BAD_REQUEST;

        StringBuilder message = new StringBuilder("Invalid arguments: ");
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            message.append(error.getField()).append(" -> ").append(error.getDefaultMessage()).append("/");
        }

        for (ObjectError error : e.getBindingResult().getGlobalErrors()) {
            message.append(error.getObjectName()).append(" -> ").append(error.getDefaultMessage()).append("/");
        }
        ApiResponse apiResponse = new ApiResponse(
                message.toString(),
                status,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiResponse, status);
    }
}
