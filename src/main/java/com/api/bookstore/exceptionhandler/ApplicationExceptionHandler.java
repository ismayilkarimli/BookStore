package com.api.bookstore.exceptionhandler;

import com.api.bookstore.exception.IdException;
import com.api.bookstore.exception.IsbnException;
import com.api.bookstore.model.error.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
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

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorTitle = "Request parameter error";
        Map<String, String> causes = Map.of("cause", "missing required parameter " + ex.getParameterName());
        ApiError apiError = new ApiError(errorTitle, causes);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorTitle = "Path variable error";
        Map<String, String> causes = Map.of("cause", "missing required path variable " + ex.getParameter());
        ApiError apiError = new ApiError(errorTitle, causes);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler({IdException.class})
    public ResponseEntity<ApiError> handleIdException(IdException ex) {
        String errorTitle = "ID error";
        Map<String, String> causes = Map.of("cause", ex.getMessage());
        ApiError error = new ApiError(errorTitle, causes);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler({IsbnException.class})
    public ResponseEntity<ApiError> handleIsbnException(IsbnException ex) {
        String errorTitle = "ISBN error";
        Map<String, String> causes = Map.of("cause", ex.getMessage());
        ApiError error = new ApiError(errorTitle, causes);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ApiError> handleIllegalArgumentException(IllegalArgumentException ex) {
        String errorTitle = "Illegal request parameter value";
        Map<String, String> causes = Map.of("cause", ex.getMessage());
        ApiError error = new ApiError(errorTitle, causes);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
