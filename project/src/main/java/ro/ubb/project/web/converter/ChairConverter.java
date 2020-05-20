package ro.ubb.project.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.project.core.model.Chair;
import ro.ubb.project.web.dto.ChairDto;

@Component
public class ChairConverter extends AbstractConverter<Chair, ChairDto> {

    @Override
    public Chair dtoToModel(ChairDto chairDto) {
        return Chair.builder()
                .cid(chairDto.getCid())
                .uid(chairDto.getUid())
                .build();
    }

    @Override
    public ChairDto modelToDto(Chair chair) {
        return ChairDto.builder()
                .cid(chair.getCid())
                .uid(chair.getUid())
                .build();
    }
}
