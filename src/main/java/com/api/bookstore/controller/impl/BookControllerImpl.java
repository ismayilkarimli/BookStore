package com.api.bookstore.controller.impl;

import com.api.bookstore.controller.BookController;
import com.api.bookstore.model.bean.Book;
import com.api.bookstore.model.dto.BookDto;
import com.api.bookstore.service.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

    @Override
    @PostMapping("")
    @ApiOperation(
            value = "add a new book",
            notes = "Entered BookDto must meet the validation requirements.",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Map<String, Long>> addBook(@Valid @RequestBody BookDto bookDto) {
        log.info("request for adding book {}", bookDto);
        Long id = bookService.addBook(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("id", id));
    }

    @Override
    @GetMapping("/all")
    @ApiOperation(
            value = "get all books",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<List<BookDto>> getAllBooks() {
        log.info("request for getting all books");
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @Override
    @GetMapping("")
    @ApiOperation(
            value = "get paginated books",
            notes = "page parameter must be an integer.",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Page<BookDto>> getPaginatedBooks(@ApiParam(value = "page to retrieve (starts from 0)", required = true) @RequestParam(defaultValue = "0") Integer page) {
        log.info("request to get books in paginated style (page {})", page);
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getPaginatedBooks(page));
    }

    @Override
    @GetMapping("/{id}")
    @ApiOperation(
            value = "search book by id",
            notes = """
            Accepts an id and returns book corresponding to the entered id.
            Throws IdException if Id is not found.
            """,
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<BookDto> searchBookById(@PathVariable Long id) {
        log.info("request to search book with id {}", id);
        BookDto bookDto = bookService.searchBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(bookDto);
    }

    @Override
    @GetMapping("/search")
    @ApiOperation(
            value = "search book(s) by title",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<List<BookDto>> searchBooksByTitle(@RequestParam String title) {
        log.info("request to search books with title {}", title);
        List<BookDto> bookDtos = bookService.searchBooksByTitle(title);
        return ResponseEntity.status(HttpStatus.OK).body(bookDtos);
    }

    @Override
    @GetMapping("/search/isbn/{isbn}")
    @ApiOperation(
            value = "search book by isbn",
            notes = """
                    ISBN must meet the ISBN validation criteria.
                    Throws IsbnException if no book with the entered ISBN is found.
                    """,
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<BookDto> searchBookByIsbn(@PathVariable String isbn) {
        log.info("request to search for book with isbn {}", isbn);
        BookDto bookDto = bookService.searchBookByIsbn(isbn);
        return ResponseEntity.status(HttpStatus.OK).body(bookDto);
    }

    @Override
    @GetMapping("/search/{year}")
    @ApiOperation(
            value = "search book(s) by title",
            notes = "year must be of type integer.",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<List<BookDto>> searchBooksBeforeOrAfterYear(@ApiParam("parameter to specify whether to return books before or after the entered year")
                                                                          @RequestParam(defaultValue = "before") String option,
                                                                      @PathVariable Integer year) {
        log.info("request to search books {} {}", option, year);
        List<BookDto> books = bookService.searchBooksBeforeOrAfterYear(option, year);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @Override
    @PutMapping("/{id}")
    @ApiOperation(
            value = "update book using an id",
            notes = "id must exist and of type Long. BookDto must meet the validation constraints.",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<BookDto> updateBook(@PathVariable("id") Long bookId,
                                                 @Valid @RequestBody BookDto bookDto) {
        log.info("request to update book with id {}", bookId);
        BookDto updatedBook = bookService.updateBook(bookId, bookDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBook);
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "delete book by id",
            notes = "id must exist and of type Long.",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable Long id) {
        log.info("request to delete book with id {}", id);
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
