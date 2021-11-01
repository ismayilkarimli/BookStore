package com.api.bookstore.controller;

import com.api.bookstore.model.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

public interface BookController {

    ResponseEntity<Map<String, Long>> addBook(BookDto bookDto);

    ResponseEntity<List<BookDto>> getAllBooks();

    ResponseEntity<Page<BookDto>> getPaginatedBooks(Integer page);

    ResponseEntity<BookDto> searchBookById(Long id);

    ResponseEntity<List<BookDto>> searchBooksByTitle(String title);

    ResponseEntity<BookDto> updateBook(Long bookId, BookDto bookDto);

    ResponseEntity<HttpStatus> deleteBook(Long id);

}
