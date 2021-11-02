package com.api.bookstore.model.dto;

import com.api.bookstore.model.bean.Author;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;

import java.time.LocalDate;
import java.util.Set;

public record BookDto(
        @NotBlank(message = "book title cannot be blank")
        String title,

        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate releaseDate,

        @NotBlank(message = "page count cannot be blank")
        @Positive(message = "page count must be positive")
        Integer pageCount,

        @JsonIgnoreProperties({ "books" })
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Set<Author> authors
) { }
