package com.api.bookstore.controller.impl;

import com.api.bookstore.model.bean.Book;
import com.api.bookstore.model.dto.BookDto;
import com.api.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookControllerImpl {

    private final BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addBook(@Valid @RequestBody BookDto bookDto) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> searchBookById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(null); // body will contain book
    }

    @GetMapping("/")
    public ResponseEntity<Object> searchBooksByTitle(@RequestParam String title) {
        return ResponseEntity.status(HttpStatus.OK).body(null); // body will contain book(s)
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateBook(@PathVariable("id") UUID bookId,
                                                 @Valid @RequestBody BookDto bookDto) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null); // body will contain updated book
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
