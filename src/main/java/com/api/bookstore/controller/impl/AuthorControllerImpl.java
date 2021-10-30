package com.api.bookstore.controller.impl;

import com.api.bookstore.controller.AuthorController;
import com.api.bookstore.model.dto.AuthorDto;
import com.api.bookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorControllerImpl implements AuthorController {

    private final AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addAuthor(@Valid @RequestBody AuthorDto authorDto) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HttpStatus> searchAuthorById(@PathVariable("id") UUID authorId) {
        return ResponseEntity.status(HttpStatus.OK).body(null); // body will contain author
    }

    @GetMapping("/")
    public ResponseEntity<HttpStatus> searchAuthorsByName(@RequestParam("name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(null); // body will contain author(s)
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateAuthor(@PathVariable("id") UUID authorId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null); // body will contain updated author
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAuthor(@PathVariable("id") UUID authorId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
