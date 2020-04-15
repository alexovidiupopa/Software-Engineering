package ro.ubb.project.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.project.web.dto.AuthorDto;
import ro.ubb.project.core.model.Author;

@Component
public class AuthorConverter extends AbstractConverter<Author, AuthorDto> implements Converter<Author, AuthorDto> {

    @Override
    public Author dtoToModel(AuthorDto authorDto) {
        return null;
    }

    @Override
    public AuthorDto modelToDto(Author author) {
        return null;
    }
}
