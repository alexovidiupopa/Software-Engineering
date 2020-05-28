package ro.ubb.project.web.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.ubb.project.web.request.ConferenceRequest;
import ro.ubb.project.web.request.MessageRequest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    @Order(1)
    void conferenceExists() {
        assert !this.conferenceController.conferenceExists();
    }

    @Test
    @Order(2)
    void createConference() {
        assert !this.conferenceController.conferenceExists();
        this.conferenceController.createConference(new ConferenceRequest("Test Conference",
                "05/25/2020 12:00:00", "05/27/2020 12:00:00",
                "05/29/2020 12:00:00", "06/01/2020 12:00:00"));
        assert this.conferenceController.conferenceExists();
    }

    @Test
    @Order(3)
    void getCurrentPhase() {
        assert this.conferenceController.getCurrentPhase().getMessage().equals("first");
    }

    @Test
    @Order(4)
    void updateDeadline() {
        this.conferenceController.updateDeadline(new MessageRequest("05/30/2020 16:00:00"));
        assert this.conferenceController.getCurrentPhase().getMessage().equals("first");
    }
}