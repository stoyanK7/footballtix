package bg.stoyank.footballtix.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class ApiException {
    private String message;
    private HttpStatus status;
    private ZonedDateTime timestamp;
}
