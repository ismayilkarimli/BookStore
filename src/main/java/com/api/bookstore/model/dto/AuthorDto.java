package com.api.bookstore.model.dto;

import com.api.bookstore.model.bean.Book;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public record AuthorDto(@NotBlank(message = "author title cannot be blank") String name,
                        @NotBlank(message = "author last title cannot be blank") String lastName,
                        Set<Book> books) {

}
