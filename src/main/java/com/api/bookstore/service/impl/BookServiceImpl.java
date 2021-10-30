package com.api.bookstore.service.impl;

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
        
    }

    @Override
    public BookDto searchBookById(UUID bookId) {
        return null;
    }

    @Override
    public List<BookDto> searchBooksByTitle(String title) {
        return null;
    }

    @Override
    public BookDto updateBook(UUID bookId) {
        return null;
    }

    @Override
    public void deleteBook(UUID id) {

    }
}
