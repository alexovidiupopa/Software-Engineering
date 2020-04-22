package ro.ubb.project.web.converter;

import ro.ubb.project.web.dto.AuthorDto;
import ro.ubb.project.core.model.Author;

public class AuthorConverter implements Converter<Author, AuthorDto> {
    @Override
    public Author dtoToModel(AuthorDto authorDto) {
        return Author.builder()
                .aid(authorDto.getAid())
                .build();
    }

    @Override
    public AuthorDto modelToDto(Author Author) {
        return AuthorDto.builder()
                .aid(Author.getAid())
                .build();
    }
}
