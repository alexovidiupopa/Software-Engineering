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
    void upload() {
    }

    @Test
    void update() {
    }

    @Test
    void updateContent() {
    }

    @Test
    void updateAbstract() {
    }

    @Test
    void uploadReview() {
    }

    @Test
    void submitReview() {
    }

    @Test
    void getAllPapers() {
        assert this.paperController.getAllPapers().getPapers().size() == 7;
        assert this.paperController.getAllPapers().getPapers().get(1).getTitle().equals("paper1");
        assert this.paperController.getAllPapers().getPapers().get(1).getTopic().equals("topic5");
    }

    @Test
    void getPapersForAuthor() {
        assert this.paperController.getPapersForAuthor(2).getPapers().size() == 2;
        assert this.paperController.getAllPapers().getPapers().get(0).getTitle().equals("paper3");
        assert this.paperController.getAllPapers().getPapers().get(1).getTitle().equals("paper7");
    }

    @Test
    void getPaperWithId() {
        assert this.paperController.getPaperWithId(1).getTitle().equals("paper1");
        assert this.paperController.getPaperWithId(7).getTitle().equals("paper7");
    }

    @Test
    void setDecision() {
    }

    @Test
    void getPapersForSession() {
    }

    @Test
    void addPaperSession() {
    }

    @Test
    void removePaperSession() {
    }

    @Test
    void addPaperAuthor() {
    }

    @Test
    void getAbstractUrl() {
    }

    @Test
    void getContentUrl() {
    }

    @Test
    void hasContent() {
    }

    @Test
    void getAbstract() {
    }

    @Test
    void getContent() {
    }

    @Test
    void bid() {
    }

    @Test
    void assignToReview() {
    }

    @Test
    void getReviewersForPaper() {
    }

    @Test
    void getPapersForReviewer() {
    }

    @Test
    void getAllExceptPcMember() {
    }

    @Test
    void acceptPaper() {
    }

    @Test
    void rejectPaper() {
    }

    @Test
    void deletePaper() {
    }

    @Test
    void reassignPaper() {
    }

    @Test
    void getAllReviews() {
        assert this.paperController.getAllReviews().size() == 10;
        assert this.paperController.getAllReviews().get(0).getPcid() == 1;
        assert this.paperController.getAllReviews().get(0).getPid() == 1;
        assert this.paperController.getAllReviews().get(0).getQualifier() == 3;
    }

    @Test
    void getPapersWithQualifiers() {
        assert this.paperController.getPapersWithQualifiers().getPapers().size() == 10;
        assert this.paperController.getPapersWithQualifiers().getPapers().size() == 10;
    }

    @Test
    void getUnscheduledPapers() {
        assert this.paperController.getUnscheduledPapers().getPapers().size() == 10;
    }

    @Test
    void addPaperToSession() {
    }

    @Test
    void deletePaperFromSession() {
    }
}