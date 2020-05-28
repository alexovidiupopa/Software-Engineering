package ro.ubb.project.web.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.ubb.project.core.service.AssignmentService;
import ro.ubb.project.web.dto.AssignmentDto;

@SpringBootTest
class AssignmentControllerTest {

    @Autowired
    private AssignmentController assignmentController;

    @Autowired
    private AssignmentService assignmentService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void submitReview() {
        this.assignmentController.submitReview(new AssignmentDto(4, 5, 3));
        assert assignmentService.getAssignmentById(4, 5).getQualifier() == 3;
        this.assignmentController.submitReview(new AssignmentDto(4, 5, 4));
        assert assignmentService.getAssignmentById(4, 5).getQualifier() == 4;
    }

    @Test
    void getReviewURL() {
        //assert this.assignmentController.getReviewURL(2, 2).getMessage().equals("/src/main/resources/reviewfile");
    }
}