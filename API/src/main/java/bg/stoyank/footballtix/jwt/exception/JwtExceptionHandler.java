package bg.stoyank.footballtix.jwt.exception;


import bg.stoyank.footballtix.exception.ApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ControllerAdvice
public class JwtExceptionHandler {
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException e) {
        HttpStatus status = UNAUTHORIZED;
        ApiResponse apiResponse = new ApiResponse(
                "Provided token has expired. Please log in again.",
                status,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiResponse, status);
    }
}
