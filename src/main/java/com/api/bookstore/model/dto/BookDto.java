package com.api.bookstore.model.dto;

import com.api.bookstore.annotation.Isbn;
import com.api.bookstore.model.bean.Author;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record BookDto(
        @NotBlank(message = "book isbn must be present")
        @Isbn(message = "invalid isbn value")
        String isbn,

        @NotBlank(message = "book title cannot be null")
        String title,

        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate releaseDate,

        @NotNull(message = "page count cannot be blank")
        @Positive(message = "page count must be positive")
        Integer pageCount,

        @JsonIgnoreProperties({ "books" })
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Set<Author> authors,

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        List<@Min(1) Long> authorIds
) { }
