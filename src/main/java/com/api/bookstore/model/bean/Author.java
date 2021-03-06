package com.api.bookstore.model.bean;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private Long authorId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "author_book",
            joinColumns = @JoinColumn(name = "author_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "book_id", nullable = false)
    )
    @ToString.Exclude
    private Set<Book> books;

    @Column(nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private Date updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Author author = (Author) o;
        return authorId != null && Objects.equals(authorId, author.authorId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}