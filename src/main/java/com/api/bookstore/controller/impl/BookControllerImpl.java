package com.api.bookstore.controller.impl;

import com.api.bookstore.controller.BookController;
import com.api.bookstore.model.dto.BookDto;
import com.api.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
@Slf4j
public class BookControllerImpl implements BookController {

    private final BookService bookService;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Map<String, Long>> addBook(@Valid @RequestBody BookDto bookDto) {
        log.info("request for adding book {}", bookDto);
        Long id = bookService.addBook(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("id", id));
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<List<BookDto>> getAllBooks() {
        log.info("request for getting all books");
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Page<BookDto>> getPaginatedBooks(@RequestParam Integer page) {
        log.info("request to get books in paginated style (page {})", page);
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getPaginatedBooks(page));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<BookDto> searchBookById(@PathVariable Long id) {
        log.info("request to search book with id {}", id);
        BookDto bookDto = bookService.searchBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(bookDto);
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<List<BookDto>> searchBooksByTitle(@RequestParam String title) {
        log.info("request to search books with title {}", title);
        List<BookDto> bookDtos = bookService.searchBooksByTitle(title);
        return ResponseEntity.status(HttpStatus.OK).body(bookDtos);
    }

    @GetMapping(value = "/search/isbn/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<BookDto> searchBookByIsbn(@PathVariable String isbn) {
        log.info("request to search for book with isbn {}", isbn);
        BookDto bookDto = bookService.searchBookByIsbn(isbn);
        return ResponseEntity.status(HttpStatus.OK).body(bookDto);
    }

    @GetMapping(value = "/search/{year}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<List<BookDto>> searchBooksBeforeOrAfterYear(@RequestParam(defaultValue = "before") String option,
                                                                      @PathVariable Integer year) {
        log.info("request to search books {} {}", option, year);
        List<BookDto> books = bookService.searchBooksBeforeOrAfterYear(option, year);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<BookDto> updateBook(@PathVariable("id") Long bookId,
                                                 @Valid @RequestBody BookDto bookDto) {
        log.info("request to update book with id {}", bookId);
        BookDto updatedBook = bookService.updateBook(bookId, bookDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBook);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable Long id) {
        log.info("request to delete book with id {}", id);
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
