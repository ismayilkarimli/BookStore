package com.api.bookstore.model.dto;

import com.api.bookstore.model.bean.Author;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class BookDto {

    @NotBlank(message = "book name cannot be blank")
    private String name;

    private Date releaseDate;

    @NotBlank(message = "page count cannot be blank")
    @Positive(message = "page count must be positive")
    private Integer pageCount;

    @NotBlank(message = "a book must have an author")
    private Author author;

}
