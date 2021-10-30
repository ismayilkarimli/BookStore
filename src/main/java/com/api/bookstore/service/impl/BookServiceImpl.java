package com.api.bookstore.service.impl;

import com.api.bookstore.mapper.BookMapper;
import com.api.bookstore.model.bean.Book;
import com.api.bookstore.model.dto.BookDto;
import com.api.bookstore.repository.BookRepository;
import com.api.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public void addBook(BookDto bookDto) {
        Book book = BookMapper.INSTANCE.bookDtoToBook(bookDto);
        bookRepository.save(book);
    }

    @Override
    public BookDto searchBookById(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("no such book"));
        BookDto dto = BookMapper.INSTANCE.bookToBookDto(book);

        return dto;
    }

    @Override
    public List<BookDto> searchBooksByTitle(String title) {
        return null;
    }

    @Override
    public BookDto updateBook(Long bookId, BookDto dto) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("no such book"));
        book.setTitle(dto.title());
        book.setPageCount(dto.pageCount());
        book.setReleaseDate(dto.releaseDate());
        Book savedBook = bookRepository.save(book);
        BookDto bookDto = BookMapper.INSTANCE.bookToBookDto(savedBook);

        return bookDto;
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
