package com.api.bookstore.annotation;

import com.api.bookstore.annotation.validator.IsbnValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// used for isbn validation.
// not suitable for the real world but good enough for this project
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = IsbnValidator.class)
public @interface Isbn {

    String message() default "{com.api.bookstore.annotation.Isbn}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
