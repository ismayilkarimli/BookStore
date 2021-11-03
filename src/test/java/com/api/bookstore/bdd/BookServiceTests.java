package com.api.bookstore.bdd;

import com.api.bookstore.model.bean.Book;
import com.api.bookstore.model.dto.BookDto;
import com.api.bookstore.repository.AuthorRepository;
import com.api.bookstore.repository.BookRepository;
import com.api.bookstore.service.BookService;
import com.api.bookstore.service.impl.BookServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    @DisplayName("Getting book by id")
    public void getBook() {
        Book mockBook = Mockito.mock(Book.class);

        // given
        given(bookRepository.findById(1L)).willReturn(Optional.of(mockBook));

        // when
        bookService.searchBookById(1L);

        // then
        then(bookRepository).should().findById(1L);
    }

    @Test
    @DisplayName("Getting books by title")
    public void searchBooksByTitle() {
        // given
        given(bookRepository.findBooksByTitleIgnoreCase(anyString())).willReturn(new ArrayList<>());

        // when
        bookService.searchBooksByTitle(anyString());

        // then
        then(bookRepository).should().findBooksByTitleIgnoreCase(anyString());
    }

}
