package com.api.bookstore.model.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private Long bookId;

    @Column(nullable = false)
    private String title;

    @Column(name = "released_date", nullable = false)
    @CreatedDate
    private Date releaseDate;

    @Column(name = "page_count", nullable = false)
    private Integer pageCount;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "books")
    @JsonIgnore
    private Set<Author> authors;

}