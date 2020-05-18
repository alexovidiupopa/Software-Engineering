package ro.ubb.project.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.project.core.model.PaperSubject;
import ro.ubb.project.core.service.KeywordService;
import ro.ubb.project.core.service.PaperSubjectService;
import ro.ubb.project.web.converter.PaperSubjectConverter;
import ro.ubb.project.web.dto.PaperSubjectDto;
import ro.ubb.project.web.response.MessageResponse;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/keyword")
public class KeywordController {

    @Autowired
    private PaperSubjectService paperSubjectService;

    @Autowired
    private PaperSubjectConverter converter;

    @Autowired
    private KeywordService keywordService;

    @RequestMapping(value = "/get-keywords/{id}", method = RequestMethod.GET)
    Collection<String> getKeywords(@PathVariable Integer id) {
        return paperSubjectService.getAllPaperSubjects().stream()
                .filter(ps -> ps.getPid() == id)
                .map(ps -> keywordService.getKeywordById(ps.getKid()).getName())
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/add-keyword", method = RequestMethod.POST)
    MessageResponse addPaperAuthor(@RequestBody PaperSubjectDto paperSubjectDto) {
        try {
            PaperSubject ps = converter.dtoToModel(paperSubjectDto);
            paperSubjectService.addPaperSubject(ps);
            return new MessageResponse("true");
        } catch (RuntimeException e) {
            return new MessageResponse("false");
        }
    }
}
