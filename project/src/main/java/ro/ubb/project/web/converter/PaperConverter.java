package ro.ubb.project.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.project.core.model.Paper;
import ro.ubb.project.web.dto.PaperDto;

@Component
public class PaperConverter extends AbstractConverter<Paper, PaperDto> {
    @Override
    public Paper dtoToModel(PaperDto paperDto) {
        return Paper.builder()
                .pid(paperDto.getPid())
                .session(paperDto.getSession())
                .title(paperDto.getTitle())
                .topic(paperDto.getTopic())
                .accepted(paperDto.getAccepted())
                .abstracturl(paperDto.getAbstracturl())
                .contenturl(paperDto.getContenturl())
                .presentationurl(paperDto.getPresentationurl())
                .build();
    }

    @Override
    public PaperDto modelToDto(Paper paper) {
        return PaperDto.builder()
                .pid(paper.getPid())
                .session(paper.getSession())
                .title(paper.getTitle())
                .topic(paper.getTopic())
                .accepted(paper.getAccepted())
                .abstracturl(paper.getAbstracturl())
                .contenturl(paper.getContenturl())
                .presentationurl(paper.getPresentationurl())
                .build();
    }
}
