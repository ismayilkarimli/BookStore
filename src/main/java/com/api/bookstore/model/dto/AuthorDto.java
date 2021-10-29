package com.api.bookstore.model.dto;

import com.api.bookstore.model.bean.Book;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public record AuthorDto(@NotBlank(message = "author name cannot be blank") String name,
                        @NotBlank(message = "author last name cannot be blank") String lastName,
                        Set<Book> books) {

}
