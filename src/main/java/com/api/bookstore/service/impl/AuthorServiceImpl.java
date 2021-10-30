package com.api.bookstore.service.impl;

import com.api.bookstore.mapper.AuthorMapper;
import com.api.bookstore.model.bean.Author;
import com.api.bookstore.model.dto.AuthorDto;
import com.api.bookstore.repository.AuthorRepository;
import com.api.bookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public void addAuthor(AuthorDto authorDto) {
        Author author = AuthorMapper.INSTANCE.authorDtoToAuthor(authorDto);
        authorRepository.save(author);
    }

    @Override
    public AuthorDto searchAuthorById(UUID authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("no such author"));
        AuthorDto authorDto = AuthorMapper.INSTANCE.authorToAuthorDto(author);

        return authorDto;
    }

    @Override
    public List<AuthorDto> searchAuthorsByName(String name) {
        return null;
    }

    @Override
    public AuthorDto updateAuthor(UUID authorId, AuthorDto dto) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("no such author"));
        author.setName(dto.name());
        author.setLastName(dto.lastName());

        Author updatedAuthor = authorRepository.save(author);
        AuthorDto authorDto = AuthorMapper.INSTANCE.authorToAuthorDto(updatedAuthor);

        return authorDto;
    }

    @Override
    public void deleteAuthor(UUID id) {
        authorRepository.deleteById(id);
    }
}
