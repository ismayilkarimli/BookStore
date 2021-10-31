package com.api.bookstore.model.dto;

import com.api.bookstore.model.bean.Book;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public record AuthorDto(
        @NotBlank(message = "author title cannot be blank")
        String name,

        @JsonProperty("lastname")
        @NotBlank(message = "author last title cannot be blank")
        String lastName,

        Set<Book> books
) { }
