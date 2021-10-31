package com.api.bookstore.controller.impl;

import com.api.bookstore.controller.BookController;
import com.api.bookstore.model.dto.BookDto;
import com.api.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@Slf4j
public class BookControllerImpl implements BookController {

    private final BookService bookService;

    @PostMapping("/")
    public ResponseEntity<Map<String, Long>> addBook(@Valid @RequestBody BookDto bookDto) {
        log.info("request for adding book {}", bookDto);
        Long id = bookService.addBook(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("id", id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> searchBookById(@PathVariable Long id) {
        log.info("request to search book with id {}", id);
        BookDto bookDto = bookService.searchBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(bookDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<BookDto>> searchBooksByTitle(@RequestParam String title) {
        log.info("request to search books with title {}", title);
        List<BookDto> bookDtos = bookService.searchBooksByTitle(title);
        return ResponseEntity.status(HttpStatus.OK).body(bookDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable("id") Long bookId,
                                                 @Valid @RequestBody BookDto bookDto) {
        log.info("request to update book with id {}", bookId);
        BookDto updatedBook = bookService.updateBook(bookId, bookDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable Long id) {
        log.info("request to delete book with id {}", id);
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
