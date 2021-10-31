package com.api.bookstore.service.impl;

import com.api.bookstore.mapper.BookMapper;
import com.api.bookstore.model.bean.Book;
import com.api.bookstore.model.dto.BookDto;
import com.api.bookstore.repository.BookRepository;
import com.api.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Long addBook(BookDto bookDto) {
        Book book = BookMapper.INSTANCE.bookDtoToBook(bookDto);
        Book saved = bookRepository.save(book);
        log.info("saved book {}", saved);

        return saved.getBookId();
    }

    @Override
    public BookDto searchBookById(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> {
                    log.error("no book with id {}", bookId);
                    throw new RuntimeException("no such book");
                });
        BookDto dto = BookMapper.INSTANCE.bookToBookDto(book);
        log.info("book {}", dto);

        return dto;
    }

    @Override
    public List<BookDto> searchBooksByTitle(String title) {
        return null;
    }

    @Override
    public BookDto updateBook(Long bookId, BookDto dto) {
        log.info("updating book with id {} and value {}", bookId, dto);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> {
                    log.error("no book with id {}", bookId);
                    throw new RuntimeException("no such book");
                });
        book.setTitle(dto.title());
        book.setAuthors(dto.authors());
        book.setPageCount(dto.pageCount());
        book.setReleaseDate(dto.releaseDate());

        Book savedBook = bookRepository.save(book);
        BookDto bookDto = BookMapper.INSTANCE.bookToBookDto(savedBook);
        log.info("updated book {}", savedBook);

        return bookDto;
    }

    @Override
    public void deleteBook(Long id) {
        log.info("deleting book with id {}", id);
        bookRepository.deleteById(id);
    }
}
