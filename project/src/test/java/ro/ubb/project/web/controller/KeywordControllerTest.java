package ro.ubb.project.web.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.ubb.project.core.model.PaperSubject;
import ro.ubb.project.core.service.PaperSubjectService;
import ro.ubb.project.web.dto.PaperSubjectDto;

@SpringBootTest
class KeywordControllerTest {

    @Autowired
    private KeywordController keywordController;

    @Autowired
    private PaperSubjectService paperSubjectService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getKeywords() {
        assert this.keywordController.getKeywords(1).size() == 3;
        assert this.keywordController.getKeywords(2).size() == 2;
        assert this.keywordController.getKeywords(5).stream().findAny().orElse("").equals("Data Structures");
    }

    @Test
    void addPaperAuthor() {
        this.keywordController.addPaperAuthor(new PaperSubjectDto(1, 4));
        assert this.keywordController.getKeywords(1).size() == 4;
        this.keywordController.addPaperAuthor(new PaperSubjectDto(1, 4));
        assert this.keywordController.getKeywords(1).size() == 4;
        paperSubjectService.deletePaperSubject(new PaperSubject(1, 4));
    }
}