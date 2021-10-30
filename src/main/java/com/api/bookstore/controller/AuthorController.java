package com.api.bookstore.controller;

import com.api.bookstore.model.dto.AuthorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

public interface AuthorController {

    ResponseEntity<HttpStatus> addAuthor(AuthorDto authorDto);

    ResponseEntity<AuthorDto> searchAuthorById(Long authorId);

    ResponseEntity<List<AuthorDto>> searchAuthorsByName(String name);

    ResponseEntity<AuthorDto> updateAuthor(Long authorId, AuthorDto authorDto);

    ResponseEntity<HttpStatus> deleteAuthor(Long authorId);

}
