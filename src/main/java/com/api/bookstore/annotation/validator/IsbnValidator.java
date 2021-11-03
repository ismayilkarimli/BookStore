package com.api.bookstore.annotation.validator;

import com.api.bookstore.annotation.Isbn;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsbnValidator implements ConstraintValidator<Isbn, String> {

    @Override
    public boolean isValid(String isbn, ConstraintValidatorContext constraintValidatorContext) {
        String cleanedIsbn = String.join("", isbn.split("-"));
        return cleanedIsbn.length() == 10 || cleanedIsbn.length() == 13;
    }

}
