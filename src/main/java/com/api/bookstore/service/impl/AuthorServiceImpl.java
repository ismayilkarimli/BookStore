package com.api.bookstore.service.impl;

import com.api.bookstore.mapper.AuthorMapper;
import com.api.bookstore.mapper.BookMapper;
import com.api.bookstore.mapper.BookMapperImpl;
import com.api.bookstore.model.bean.Author;
import com.api.bookstore.model.dto.AuthorDto;
import com.api.bookstore.repository.AuthorRepository;
import com.api.bookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Long addAuthor(AuthorDto authorDto) {
        Author author = AuthorMapper.INSTANCE.authorDtoToAuthor(authorDto);
        Author saved = authorRepository.save(author);
        log.info("author: {}", saved);

        return saved.getAuthorId();
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        log.info("all authors: {}", authors);
        List<AuthorDto> authorDtos = authors.stream()
                .map(AuthorMapper.INSTANCE::authorToAuthorDto)
                .collect(Collectors.toList());

        return authorDtos;
    }

    @Override
    public AuthorDto searchAuthorById(Long authorId) {
        log.info("searching for author with id {}", authorId);
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> {
                    log.error("no author found with id {}", authorId);
                    throw new RuntimeException("no such author");
                });
        log.info("found author {}", author);
        AuthorDto authorDto = AuthorMapper.INSTANCE.authorToAuthorDto(author);

        return authorDto;
    }

    @Override
    public List<AuthorDto> searchAuthorsByName(String name) {
        log.info("searching for authors with name {}", name);
        List<Author> authors = authorRepository.findAuthorsByNameIgnoreCaseOrderByName(name);
        List<AuthorDto> authorDtos = authors.stream()
                .map(AuthorMapper.INSTANCE::authorToAuthorDto)
                .collect(Collectors.toList());

        return authorDtos;
    }

    @Override
    public Page<AuthorDto> getPaginatedAuthors(Integer page) {
        log.info("fetching page {}", page);
        final int pageSize = 5;
        Page<Author> all = authorRepository.findAll(PageRequest.of(page, pageSize));
        Page<AuthorDto> authorDtoPage = all.map(AuthorMapper.INSTANCE::authorToAuthorDto);
        log.info("page content {}", authorDtoPage.getContent());

        return authorDtoPage;
    }

    @Override
    public AuthorDto updateAuthor(Long authorId, AuthorDto dto) {
        log.info("updating author with id {}", authorId);
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> {
                    log.error("no author with id {}", authorId);
                    throw new RuntimeException("no such author");
                });
        author.setName(dto.name());
        author.setLastName(dto.lastName());
        author.setBooks(dto.books());

        Author updatedAuthor = authorRepository.save(author);
        AuthorDto authorDto = AuthorMapper.INSTANCE.authorToAuthorDto(updatedAuthor);
        log.info("updated author {}", author);

        return authorDto;
    }

    @Override
    public void deleteAuthor(Long id) {
        log.info("deleting author with id {}", id);
        authorRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("no author with id {}", id);
                    throw new RuntimeException("no such author");
                });
        authorRepository.deleteById(id);
    }
}
