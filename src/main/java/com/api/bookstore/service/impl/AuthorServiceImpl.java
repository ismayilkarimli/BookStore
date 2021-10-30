package com.api.bookstore.service.impl;

import com.api.bookstore.model.dto.AuthorDto;
import com.api.bookstore.repository.AuthorRepository;
import com.api.bookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public void addAuthor(AuthorDto authorDto) {

    }

    @Override
    public AuthorDto searchAuthorById(UUID authorId) {
        return null;
    }

    @Override
    public List<AuthorDto> searchAuthorsByName(String name) {
        return null;
    }

    @Override
    public AuthorDto updateAuthor(UUID authorId) {
        return null;
    }

    @Override
    public void deleteAuthor(UUID id) {

    }
}
