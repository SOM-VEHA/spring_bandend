package com.spring_bandend.spring_bandend.exception;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.spring_bandend.spring_bandend.base.BaseError;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        BaseError<String> error = BaseError.<String>builder()
                .status(false)
                .code(HttpStatus.NOT_FOUND.value())
                .message("Resource not found")
                .errors(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
