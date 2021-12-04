package bg.stoyank.footballtix.footballmatch.exception;

import bg.stoyank.footballtix.exception.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class FootballMatchExceptionHandler {
    @ExceptionHandler(FootballMatchNotFoundException.class)
    public ResponseEntity<Object> handleFootballMatchNotFoundException(FootballMatchNotFoundException e) {
        HttpStatus status = NOT_FOUND;
        ApiResponse apiResponse = new ApiResponse(
                e.getMessage(),
                status,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiResponse, status);
    }
}
