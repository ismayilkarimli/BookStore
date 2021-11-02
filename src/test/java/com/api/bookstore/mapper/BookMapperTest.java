package com.api.bookstore.mapper;

import com.api.bookstore.model.bean.Book;
import com.api.bookstore.model.dto.BookDto;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BookMapperTest {

    @Test
    public void shouldMapBookToDto() {
        LocalDate date = LocalDate.parse("01-10-1999", DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        // given
        Book book = new Book();
        book.setTitle("Pragmatic Programmer");
        book.setPageCount(320);
        book.setReleaseDate(date);
        book.setAuthors(Collections.emptySet());

        // when
        BookDto bookDto = BookMapper.INSTANCE.bookToBookDto(book);

        // then
        assertThat(bookDto).isNotNull();
        assertThat(bookDto.title()).isEqualTo("Pragmatic Programmer");
        assertThat(bookDto.pageCount()).isEqualTo(320);
        assertThat(bookDto.releaseDate()).isEqualTo(date);
        assertThat(bookDto.authors()).isNotNull();
    }

    @Test
    public void shouldMapDtoToBook() {
        LocalDate date = LocalDate.parse("01-10-1999", DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        // given
        BookDto bookDto = new BookDto("Pragmatic Programmer", date, 320, Collections.emptySet(), List.of(1L));

        // when
        Book book = BookMapper.INSTANCE.bookDtoToBook(bookDto);

        // then
        assertThat(book).isNotNull();
        assertThat(book.getTitle()).isEqualTo("Pragmatic Programmer");
        assertThat(book.getPageCount()).isEqualTo(320);
        assertThat(book.getReleaseDate()).isEqualTo(date);
        assertThat(book.getAuthors()).isNotNull();
    }

}
