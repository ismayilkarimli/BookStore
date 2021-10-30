package com.api.bookstore.model.dto;

import com.api.bookstore.model.bean.Author;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.sql.Date;
import java.util.Set;

public record BookDto(@NotBlank(message = "book title cannot be blank") String title,
                      Date releaseDate,
                      @NotBlank(message = "page count cannot be blank") @Positive(message = "page count must be positive") Integer pageCount,
                      @NotBlank(message = "a book must have an author") Set<Author> authors) {

}
