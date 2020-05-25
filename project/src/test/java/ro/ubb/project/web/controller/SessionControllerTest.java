package ro.ubb.project.web.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.ubb.project.web.request.CreateSessionRequest;

import java.sql.Time;

@SpringBootTest
class SessionControllerTest {

    @Autowired
    private SessionController sessionController;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getSessionWithId() {
        assert this.sessionController.getSessionWithId(2).getSid() == 2;
        assert this.sessionController.getSessionWithId(3).getSupervisor() == 4;
        assert this.sessionController.getSessionWithId(6).getRid() == 1;
    }

    @Test
    void getAllSessions() {
        assert this.sessionController.getAllSessions().getSessions().size() == 6;
    }

    @Test
    void getAvailable() {
        assert this.sessionController.getAllSessions().getSessions().size() == 6;
    }

    @Test
    void addSession() {
        this.sessionController.addSession(new CreateSessionRequest());
    }
}