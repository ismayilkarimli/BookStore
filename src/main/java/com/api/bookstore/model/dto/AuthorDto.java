package com.api.bookstore.model.dto;

import com.api.bookstore.model.bean.Book;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

public record AuthorDto(
        @NotBlank(message = "author name cannot be blank")
        @Pattern(regexp = "^[A-Za-z-' ]*$", message = "author name can only include letters, space, dash, and apostrophe")
        String name,

        @JsonProperty("lastName")
        @NotBlank(message = "author last name cannot be blank")
        @Pattern(regexp = "^[A-Za-z-' ]*$", message = "author last name can only include letters, space, dash, and apostrophe")
        String lastName,

        @JsonIgnoreProperties({ "authors", "createdAt", "updatedAt" })
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Set<Book> books,

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        List<@Min(1) Long> bookIds
) { }
