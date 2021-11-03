package com.api.bookstore.repository;


import com.api.bookstore.model.bean.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBooksByTitleIgnoreCase(String title);

    Optional<Book> findBookByIsbn(String isbn);

    List<Book> findBooksByReleaseDateBefore(LocalDate year);

    List<Book> findBooksByReleaseDateAfter(LocalDate year);
}
