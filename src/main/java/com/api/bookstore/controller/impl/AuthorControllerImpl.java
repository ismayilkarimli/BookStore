package com.api.bookstore.controller.impl;

import com.api.bookstore.controller.AuthorController;
import com.api.bookstore.model.dto.AuthorDto;
import com.api.bookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorControllerImpl implements AuthorController {

    private final AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addAuthor(@Valid @RequestBody AuthorDto authorDto) {
        authorService.addAuthor(authorDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> searchAuthorById(@PathVariable("id") Long authorId) {
        AuthorDto authorDto = authorService.searchAuthorById(authorId);
        return ResponseEntity.status(HttpStatus.OK).body(authorDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<AuthorDto>> searchAuthorsByName(@RequestParam("name") String name) {
        List<AuthorDto> authorDtos = authorService.searchAuthorsByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(authorDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable("id") Long authorId,
                                                  @Valid @RequestBody AuthorDto authorDto) {
        AuthorDto updatedAuthor = authorService.updateAuthor(authorId, authorDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(updatedAuthor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAuthor(@PathVariable("id") Long authorId) {
        authorService.deleteAuthor(authorId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
