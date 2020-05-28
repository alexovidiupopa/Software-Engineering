package ro.ubb.project.web.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PaperControllerTest {

    @Autowired
    private PaperController paperController;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllPapers() {
        assert this.paperController.getAllPapers().getPapers().size() == 7;
        assert this.paperController.getAllPapers().getPapers().get(0).getTitle().equals("paper1");
        assert this.paperController.getAllPapers().getPapers().get(3).getTitle().equals("paper4");
    }

    @Test
    void getPaperWithId() {
        assert this.paperController.getPaperWithId(1).getTitle().equals("paper1");
        assert this.paperController.getPaperWithId(7).getTitle().equals("paper7");
    }

    @Test
    void getAllReviews() {
        assert this.paperController.getAllReviews().size() == 10;
        assert this.paperController.getAllReviews().get(0).getPcid() == 1;
        assert this.paperController.getAllReviews().get(0).getPid() == 1;
        assert this.paperController.getAllReviews().get(0).getQualifier() == 3;
    }

    @Test
    void getUnscheduledPapers() {
        assert this.paperController.getUnscheduledPapers().getPapers().size() == 0;
    }
}