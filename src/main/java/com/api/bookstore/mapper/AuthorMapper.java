package com.api.bookstore.mapper;

import com.api.bookstore.model.bean.Author;
import com.api.bookstore.model.bean.Book;
import com.api.bookstore.model.dto.AuthorDto;
import com.api.bookstore.model.dto.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDto authorToAuthorDto(Author author);
    Author authorDtoToAuthor(AuthorDto dto);
}
