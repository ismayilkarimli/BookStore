package com.api.bookstore.service.impl;

import com.api.bookstore.exception.IdException;
import com.api.bookstore.mapper.AuthorMapper;
import com.api.bookstore.model.bean.Author;
import com.api.bookstore.model.bean.Book;
import com.api.bookstore.model.dto.AuthorDto;
import com.api.bookstore.repository.AuthorRepository;
import com.api.bookstore.repository.BookRepository;
import com.api.bookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Transactional
    @Override
    public Long addAuthor(AuthorDto authorDto) {
        log.info("saving author {}", authorDto);
        Author author = AuthorMapper.INSTANCE.authorDtoToAuthor(authorDto);
        List<Book> books = new ArrayList<>();
        if (authorDto.bookIds() != null) {
            books = bookRepository.findAllById(authorDto.bookIds());
            author.setBooks(new HashSet<>(books));
            books.forEach(book -> book.getAuthors().add(author));
        }
        Author saved = authorRepository.save(author);
        log.info("saved author: {}", saved);
        bookRepository.saveAll(books);

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
                    throw new IdException("no author with id " + authorId);
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

    @Transactional
    @Override
    public AuthorDto updateAuthor(Long authorId, AuthorDto dto) {
        log.info("updating author with id {}", authorId);
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> {
                    log.error("no author with id {}", authorId);
                    throw new IdException("no author with id " + authorId);
                });
        List<Book> books = new ArrayList<>(author.getBooks());
        if (dto.bookIds() != null) {
            books.forEach(book -> book.getAuthors().remove(author));
            books = bookRepository.findAllById(dto.bookIds());
            books.forEach(book -> book.getAuthors().add(author));
            author.setBooks(new HashSet<>(books));
        }
        author.setName(dto.name());
        author.setLastName(dto.lastName());
        author.setBooks(dto.books());
        Author updatedAuthor = authorRepository.save(author);
        log.info("updated author {}", updatedAuthor);
        bookRepository.saveAll(books);
        AuthorDto authorDto = AuthorMapper.INSTANCE.authorToAuthorDto(updatedAuthor);

        return authorDto;
    }

    @Override
    public void deleteAuthor(Long id) {
        log.info("deleting author with id {}", id);
        authorRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("no author with id {}", id);
                    throw new IdException("no author with id " + id);
                });
        authorRepository.deleteById(id);
    }
}
