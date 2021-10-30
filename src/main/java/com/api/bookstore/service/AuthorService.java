package com.api.bookstore.service;

import com.api.bookstore.model.bean.Author;
import com.api.bookstore.model.dto.AuthorDto;

import java.util.List;
import java.util.UUID;

public interface AuthorService {

    void addAuthor(AuthorDto authorDto);

    AuthorDto searchAuthorById(UUID authorId);

    List<AuthorDto> searchAuthorsByName(String name);

    AuthorDto updateAuthor(UUID authorId);

    void deleteAuthor(UUID id);

}
