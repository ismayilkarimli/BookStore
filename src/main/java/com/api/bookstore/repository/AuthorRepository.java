package com.api.bookstore.repository;

import com.api.bookstore.model.bean.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findAuthorsByNameIgnoreCaseOrderByName(String name);

}
