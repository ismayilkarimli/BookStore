package com.api.bookstore.mapper;

import com.api.bookstore.model.bean.Book;
import com.api.bookstore.model.dto.BookDto;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class BookMapperTest {

    @Test
    public void shouldMapBookToDto() {
        // given
        Book book = new Book();
        book.setName("Pragmatic Programmer");
        book.setPageCount(320);
        book.setReleaseDate(new Date(1L));
        book.setAuthors(Collections.emptySet());

        // when
        BookDto bookDto = BookMapper.INSTANCE.bookToBookDto(book);

        // then
        assertThat(bookDto).isNotNull();
        assertThat(bookDto.name()).isEqualTo("Pragmatic Programmer");
        assertThat(bookDto.pageCount()).isEqualTo(320);
        assertThat(bookDto.releaseDate()).isEqualTo(new Date(1L));
        assertThat(bookDto.authors()).isNotNull();
    }

    @Test
    public void shouldMapDtoToBook() {
        // given
        BookDto bookDto = new BookDto("Pragmatic Programmer", new Date(1L), 320,
                Collections.emptySet());

        // when
        Book book = BookMapper.INSTANCE.bookDtoToBook(bookDto);

        // then
        assertThat(book).isNotNull();
        assertThat(book.getName()).isEqualTo("Pragmatic Programmer");
        assertThat(book.getPageCount()).isEqualTo(320);
        assertThat(book.getReleaseDate()).isEqualTo(new Date(1L));
        assertThat(book.getAuthors()).isNotNull();
    }

}
