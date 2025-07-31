package io.devotel.exceptions;

import io.devotel.common.GeneralResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralResponseDto<String>> handleUnexpectedException(Exception ex) {
        logger.error("Unhandled exception occurred", ex);

        GeneralResponseDto<String> response = GeneralResponseDto.<String>builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("An unexpected internal error occurred.")
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralResponseDto<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String error = ex.getBindingResult()
                         .getFieldErrors()
                         .stream()
                         .map(e -> e.getField() + ": " + e.getDefaultMessage())
                         .findFirst()
                         .orElse("Invalid input");

        return new ResponseEntity<>(
                new GeneralResponseDto<>(HttpStatus.BAD_REQUEST.value(), error, null),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<GeneralResponseDto<String>> handleUserNotFoundException(UserNotFoundException ex) {
        logger.error("User not found: {}", ex.getMessage(), ex);

        GeneralResponseDto<String> response = new GeneralResponseDto<>(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
