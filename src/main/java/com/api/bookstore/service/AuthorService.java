package com.api.bookstore.service;

import com.api.bookstore.model.bean.Author;
import com.api.bookstore.model.dto.AuthorDto;

import java.util.List;
import java.util.UUID;

public interface AuthorService {

    void addAuthor(AuthorDto authorDto);

    AuthorDto searchAuthorById(Long authorId);

    List<AuthorDto> searchAuthorsByName(String name);

    AuthorDto updateAuthor(Long authorId, AuthorDto authorDto);

    void deleteAuthor(Long id);

}
