package com.api.bookstore.service.impl;

import com.api.bookstore.exception.IdException;
import com.api.bookstore.exception.IsbnException;
import com.api.bookstore.mapper.BookMapper;
import com.api.bookstore.model.bean.Author;
import com.api.bookstore.model.bean.Book;
import com.api.bookstore.model.dto.BookDto;
import com.api.bookstore.repository.AuthorRepository;
import com.api.bookstore.repository.BookRepository;
import com.api.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Transactional
    @Override
    public Long addBook(BookDto bookDto) {
        Book book = BookMapper.INSTANCE.bookDtoToBook(bookDto);
        List<Author> authors = new ArrayList<>();
        if (bookDto.authorIds() != null) {
            authors = authorRepository.findAllById(bookDto.authorIds());
            authors.forEach(author -> author.getBooks().add(book));
            book.setAuthors(new HashSet<>(authors));
        }
        Book saved = bookRepository.save(book);
        authorRepository.saveAll(authors);
        log.info("saved book {}", saved);

        return saved.getBookId();
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        log.info("all books: {}", books);
        List<BookDto> bookDtos = books.stream()
                .map(BookMapper.INSTANCE::bookToBookDto)
                .collect(Collectors.toList());

        return bookDtos;
    }

    @Override
    public BookDto searchBookById(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> {
                    log.error("no book with id {}", bookId);
                    throw new IdException("no such book");
                });
        BookDto dto = BookMapper.INSTANCE.bookToBookDto(book);
        log.info("book {}", dto);
        return dto;
    }

    @Override
    public BookDto searchBookByIsbn(String isbn) {
        log.info("searching for book with isbn {}", isbn);
        Book book = bookRepository.findBookByIsbn(isbn).orElseThrow(() -> {
            log.error("no book with isbn {}", isbn);
            throw new IsbnException("no such book");
        });
        log.info("book with isbn {}: {}", isbn, book);
        return BookMapper.INSTANCE.bookToBookDto(book);
    }

    @Override
    public List<BookDto> searchBooksBeforeOrAfterYear(String option, Integer year) {
        List<Book> booksByRelease;
        if (option.equals("before")) {
            booksByRelease = bookRepository.findBooksByReleaseDateBefore(LocalDate.of(year, Month.DECEMBER, 31));
            log.info("list of books before {}: {}", year, booksByRelease);
        } else if (option.equals("after")) {
            booksByRelease = bookRepository.findBooksByReleaseDateAfter(LocalDate.of(year, Month.DECEMBER, 31));
            log.info("list of books after {}: {}", year, booksByRelease);
        } else {
            log.error("invalid option");
            throw new IllegalArgumentException("no such option");
        }

        return booksByRelease.stream()
                .map(BookMapper.INSTANCE::bookToBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> searchBooksByTitle(String title) {
        List<Book> books = bookRepository.findBooksByTitleIgnoreCase(title);
        log.info("books with {} in the title: {}", title, books);
        List<BookDto> bookDtos = books.stream()
                .map(BookMapper.INSTANCE::bookToBookDto)
                .collect(Collectors.toList());

        return bookDtos;
    }

    @Override
    public Page<BookDto> getPaginatedBooks(Integer page) {
        final int pageSize = 5;
        Page<Book> all = bookRepository.findAll(PageRequest.of(page, pageSize));
        Page<BookDto> bookDtoPage = all.map(BookMapper.INSTANCE::bookToBookDto);
        return bookDtoPage;
    }

    @Transactional
    @Override
    public BookDto updateBook(Long bookId, BookDto dto) {
        log.info("updating book with id {} and value {}", bookId, dto);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> {
                    log.error("no book with id {}", bookId);
                    throw new IdException("no such book");
                });
        List<Author> authors = new ArrayList<>(book.getAuthors());
        if (dto.authorIds() != null) {
            authors.forEach(author -> author.getBooks().remove(book));
            authors = authorRepository.findAllById(dto.authorIds());
            authors.forEach(author -> author.getBooks().add(book));
            book.setAuthors(new HashSet<>(authors));
        }
        book.setTitle(dto.title());
        book.setPageCount(dto.pageCount());
        book.setReleaseDate(dto.releaseDate());
        Book savedBook = bookRepository.save(book);
        log.info("updated book {}", savedBook);
        authorRepository.saveAll(authors);
        BookDto bookDto = BookMapper.INSTANCE.bookToBookDto(savedBook);

        return bookDto;
    }

    @Override
    public void deleteBook(Long id) {
        log.info("deleting book with id {}", id);
        bookRepository.findById(id).orElseThrow(() -> {
            log.error("Could not delete. No book with id {}", id);
            throw new IdException("no such book");
        });
        bookRepository.deleteById(id);
    }
}
