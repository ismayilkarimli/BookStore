package com.api.bookstore.model.dto;

import com.api.bookstore.model.bean.Book;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class AuthorDto {

    @NotBlank(message = "author name cannot be blank")
    private String name;

    @NotBlank(message = "author last name cannot be blank")
    private String lastName;

    private List<Book> books;

}
