package lll.backend.common.handler;

import lll.backend.common.exception.ExceptionDetails;
import lll.backend.common.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlers {

    @ExceptionHandler
    public ResponseEntity<ExceptionDetails> handleException(Exception e) {
        log.error("[INTERNAL ERROR]: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionDetails(e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionDetails> handleNotFoundException(NotFoundException e) {
        log.error("[NOT FOUND ERROR]: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionDetails(e.getMessage()));
    }
}
