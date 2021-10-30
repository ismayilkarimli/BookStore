package com.api.bookstore.service;

import com.api.bookstore.model.dto.BookDto;

import java.util.List;
import java.util.UUID;

public interface BookService {

    void addBook(BookDto bookDto);

    BookDto searchBookById(UUID bookId);

    List<BookDto> searchBooksByTitle(String title);

    BookDto updateBook(UUID bookId);

    void deleteBook(UUID id);

}
