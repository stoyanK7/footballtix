package bg.stoyank.footballtix.controller.exception;

import bg.stoyank.footballtix.model.exception.FootballMatchNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FootballMatchExceptionController {
    @ExceptionHandler(FootballMatchNotFoundException.class)
    public ResponseEntity<Object> exception(FootballMatchNotFoundException exception) {
        return new ResponseEntity<>("Football match not found!", HttpStatus.NOT_FOUND);
    }


}
