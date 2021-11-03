package com.api.bookstore.exceptionhandler;

import com.api.bookstore.exception.IdException;
import com.api.bookstore.model.error.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorTitle = "Validation error";
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        ApiError error = new ApiError(errorTitle, errors);

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler({IdException.class})
    public ResponseEntity<ApiError> handleResourceNotFound() {
        String errorTitle = "Resource with the entered ID does not exist";
        ApiError error = new ApiError(errorTitle);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
