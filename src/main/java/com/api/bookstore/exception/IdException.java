package com.api.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Resource with the requested ID does not exist")
public class IdException extends RuntimeException {

    public IdException(String message) {
        super(message);
    }

}
