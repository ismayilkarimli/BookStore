package com.api.bookstore.model.dto;

import com.api.bookstore.model.bean.Book;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

public record AuthorDto(
        @NotBlank(message = "author name cannot be blank")
        String name,

        @JsonProperty("lastname")
        @NotBlank(message = "author last name cannot be blank")
        String lastName,

        @JsonIgnoreProperties({ "authors", "createdAt", "updatedAt" })
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Set<Book> books,

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        List<Long> bookIds
) { }
