package com.api.bookstore.controller.impl;

import com.api.bookstore.controller.AuthorController;
import com.api.bookstore.model.dto.AuthorDto;
import com.api.bookstore.service.AuthorService;
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
@RequestMapping("/author")
@RequiredArgsConstructor
@Slf4j
public class AuthorControllerImpl implements AuthorController {

    private final AuthorService authorService;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Map<String, Long>> addAuthor(@Valid @RequestBody AuthorDto authorDto) {
        log.info("request for adding author {}", authorDto);
        Long id = authorService.addAuthor(authorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("id", id));
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        log.info("request to get all authors");
        List<AuthorDto> allAuthors = authorService.getAllAuthors();
        return ResponseEntity.ok(allAuthors);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Page<AuthorDto>> getPaginatedAuthors(@RequestParam Integer page) {
        log.info("request to get authors in paginated style (page {})", page);
        Page<AuthorDto> paginatedAuthors = authorService.getPaginatedAuthors(page);
        return ResponseEntity.ok(paginatedAuthors);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<AuthorDto> searchAuthorById(@PathVariable("id") Long authorId) {
        log.info("request to search author with id {}", authorId);
        AuthorDto authorDto = authorService.searchAuthorById(authorId);
        return ResponseEntity.status(HttpStatus.OK).body(authorDto);
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<List<AuthorDto>> searchAuthorsByName(@RequestParam("name") String name) {
        log.info("request to search author with name {}", name);
        List<AuthorDto> authorDtos = authorService.searchAuthorsByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(authorDtos);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable("id") Long authorId,
                                                  @Valid @RequestBody AuthorDto authorDto) {
        log.info("put request for updating author with id {}", authorId);
        AuthorDto updatedAuthor = authorService.updateAuthor(authorId, authorDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedAuthor);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<HttpStatus> deleteAuthor(@PathVariable("id") Long authorId) {
        log.info("request to delete author with id {}", authorId);
        authorService.deleteAuthor(authorId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
