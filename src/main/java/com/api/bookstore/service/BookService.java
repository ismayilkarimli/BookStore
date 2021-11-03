package com.api.bookstore.service;

import com.api.bookstore.model.dto.BookDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {

    Long addBook(BookDto bookDto);

    List<BookDto> getAllBooks();

    BookDto searchBookById(Long bookId);

    BookDto searchBookByIsbn(String isbn);

    List<BookDto> searchBooksBeforeOrAfterYear(String option, Integer year);

    List<BookDto> searchBooksByTitle(String title);

    Page<BookDto> getPaginatedBooks(Integer page);

    BookDto updateBook(Long bookId, BookDto bookDto);

    void deleteBook(Long id);

}
