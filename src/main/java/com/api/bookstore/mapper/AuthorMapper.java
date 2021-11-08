package com.api.bookstore.mapper;

import com.api.bookstore.model.bean.Author;
import com.api.bookstore.model.dto.AuthorDto;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDto authorToAuthorDto(Author author);

    Author authorDtoToAuthor(AuthorDto dto);

}
