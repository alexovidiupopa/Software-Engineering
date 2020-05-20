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
                .aid(paperDto.getAuthorId())
                .session(paperDto.getSession())
                .title(paperDto.getTitle())
                .accepted(paperDto.getAccepted())
                .abstractUrl(paperDto.getAbstractUrl())
                .contentUrl(paperDto.getContentUrl())
                .build();
    }

    @Override
    public PaperDto modelToDto(Paper paper) {
        return PaperDto.builder()
                .pid(paper.getPid())
                .authorId(paper.getAid())
                .session(paper.getSession())
                .title(paper.getTitle())
                .accepted(paper.getAccepted())
                .abstractUrl(paper.getAbstractUrl())
                .contentUrl(paper.getContentUrl())
                .build();
    }
}
