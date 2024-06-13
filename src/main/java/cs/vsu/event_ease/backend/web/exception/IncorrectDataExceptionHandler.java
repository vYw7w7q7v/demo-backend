package cs.vsu.event_ease.backend.web.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class IncorrectDataExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IncorrectDataException.class)
    public ResponseEntity<String> handleIncorrectDataException(IncorrectDataException exception) {
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(exception.getMessage());
    }

}
