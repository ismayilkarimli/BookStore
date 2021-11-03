package com.api.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class IdException extends RuntimeException {

    public IdException(String message) {
        super(message);
    }

}
