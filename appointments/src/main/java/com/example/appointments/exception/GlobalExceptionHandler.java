package com.example.appointments.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeExceptions(RuntimeException ex, WebRequest request) {
        // Customize the error response for RuntimeException class
        ErrorResponse errorResponse = new ErrorResponse("Runtime Exception occurred", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    protected ResponseEntity<Object> handleBindException(BindException ex, org.springframework.http.HttpHeaders headers,
                                                         HttpStatus status, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", status.value());

        // Get all errors
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        body.put("errors", errors);

        return new ResponseEntity<>(body, status);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("message", "Something went wrong.");

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    static class ErrorResponse {
        private final String message;
        private final String details;

        public ErrorResponse(String message, String details) {
            this.message = message;
            this.details = details;
        }

        public String getMessage() {
            return message;
        }

        public String getDetails() {
            return details;
        }
    }
}