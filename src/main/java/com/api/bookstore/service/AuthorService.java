package com.api.bookstore.service;

import com.api.bookstore.model.bean.Author;
import com.api.bookstore.model.dto.AuthorDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AuthorService {

    Long addAuthor(AuthorDto authorDto);

    List<AuthorDto> getAllAuthors();

    AuthorDto searchAuthorById(Long authorId);

    List<AuthorDto> searchAuthorsByName(String name);

    Page<AuthorDto> getPaginatedAuthors(Integer page);

    AuthorDto updateAuthor(Long authorId, AuthorDto authorDto);

    void deleteAuthor(Long id);

}
