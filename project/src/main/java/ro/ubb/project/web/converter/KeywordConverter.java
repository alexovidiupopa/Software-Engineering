package ro.ubb.project.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.project.core.model.Keyword;
import ro.ubb.project.web.dto.KeywordDto;

@Component
public class KeywordConverter extends AbstractConverter<Keyword, KeywordDto> implements Converter<Keyword, KeywordDto> {

    @Override
    public Keyword dtoToModel(KeywordDto keywordDto) {
        return Keyword.builder()
                .kid(keywordDto.getKid())
                .name(keywordDto.getName())
                .build();
    }

    @Override
    public KeywordDto modelToDto(Keyword keyword) {
        return KeywordDto.builder()
                .kid(keyword.getKid())
                .name(keyword.getName())
                .build();
    }
}
