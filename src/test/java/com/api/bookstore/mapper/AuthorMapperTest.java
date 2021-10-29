package com.api.bookstore.mapper;

import com.api.bookstore.model.bean.Author;
import com.api.bookstore.model.dto.AuthorDto;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthorMapperTest {

    @Test
    public void shouldMapAuthorToAuthorDto() {
        // given
        Author author = new Author();
        author.setName("Mark");
        author.setLastName("Twain");

        // when
        AuthorDto authorDto = AuthorMapper.INSTANCE.authorToAuthorDto(author);

        // then
        assertThat(authorDto).isNotNull();
        assertThat(authorDto.name()).isEqualTo("Mark");
        assertThat(authorDto.lastName()).isEqualTo("Twain");
    }

    @Test
    public void shouldMapDtoToAuthor() {
        // given
        AuthorDto authorDto = new AuthorDto("Mark", "Twain", Collections.emptySet());

        // when
        Author author = AuthorMapper.INSTANCE.authorDtoToAuthor(authorDto);

        // then
        assertThat(author).isNotNull();
        assertThat(author.getName()).isEqualTo("Mark");
        assertThat(author.getLastName()).isEqualTo("Twain");
        assertThat(author.getBooks()).isNotNull();
    }
    
}
