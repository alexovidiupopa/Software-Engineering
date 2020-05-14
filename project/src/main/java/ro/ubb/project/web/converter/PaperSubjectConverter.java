package ro.ubb.project.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.project.core.model.PaperSubject;
import ro.ubb.project.web.dto.PaperSubjectDto;

@Component
public class PaperSubjectConverter extends AbstractConverter<PaperSubject, PaperSubjectDto> implements Converter<PaperSubject, PaperSubjectDto> {

    @Override
    public PaperSubject dtoToModel(PaperSubjectDto paperSubjectDto) {
        return PaperSubject.builder()
                .kid(paperSubjectDto.getKid())
                .pid(paperSubjectDto.getPid())
                .build();
    }

    @Override
    public PaperSubjectDto modelToDto(PaperSubject paperSubject) {
        return PaperSubjectDto.builder()
                .kid(paperSubject.getKid())
                .pid(paperSubject.getPid())
                .build();
    }
}
