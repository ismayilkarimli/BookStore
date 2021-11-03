package com.api.bookstore.bdd;

import com.api.bookstore.model.bean.Author;
import com.api.bookstore.model.bean.Book;
import com.api.bookstore.repository.AuthorRepository;
import com.api.bookstore.repository.BookRepository;
import com.api.bookstore.service.impl.AuthorServiceImpl;
import com.api.bookstore.service.impl.BookServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTests {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Test
    @DisplayName("Getting author by id")
    public void getAuthor() {
        Author mockAuthor = Mockito.mock(Author.class);

        // given
        given(authorRepository.findById(1L)).willReturn(Optional.of(mockAuthor));

        // when
        authorService.searchAuthorById(1L);

        // then
        then(authorRepository).should().findById(1L);
    }

    @Test
    @DisplayName("Getting authors by name")
    public void searchAuthorsByTitle() {
        // given
        given(authorRepository.findAuthorsByNameIgnoreCaseOrderByName(anyString())).willReturn(new ArrayList<>());

        // when
        authorService.searchAuthorsByName(anyString());

        // then
        then(authorRepository).should().findAuthorsByNameIgnoreCaseOrderByName(anyString());
    }


}
