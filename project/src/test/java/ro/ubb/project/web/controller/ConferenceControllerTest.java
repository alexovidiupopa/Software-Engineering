package ro.ubb.project.web.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.ubb.project.web.request.ConferenceRequest;
import ro.ubb.project.web.request.MessageRequest;

@SpringBootTest
class ConferenceControllerTest {

    @Autowired
    private ConferenceController conferenceController;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void conferenceExists() {
        assert !this.conferenceController.conferenceExists();
    }

    @Test
    void createConference() {
        this.conferenceController.createConference(new ConferenceRequest("Test Conference",
                "12/06/2020 12:00:00", "13/06/2020 12:00:00",
                "14/06/2020 12:00:00", "15/06/2020 12:00:00"));
        assert this.conferenceController.conferenceExists();
    }

    @Test
    void getCurrentPhase() {
        System.out.println(this.conferenceController.getCurrentPhase().getMessage());
    }

    @Test
    void updateDeadline() {
        this.conferenceController.updateDeadline(new MessageRequest("12/06/2020 16:00:00"));
    }
}