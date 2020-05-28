package ro.ubb.project.web.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.ubb.project.core.model.Ticket;
import ro.ubb.project.core.service.TicketService;
import ro.ubb.project.web.dto.PaperDto;
import ro.ubb.project.web.request.CreateSessionRequest;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

@SpringBootTest
class SessionControllerTest {

    @Autowired
    private SessionController sessionController;

    @Autowired
    private TicketService ticketService;

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
        assert this.sessionController.getAllSessions().getSessions().get(4).getSupervisor() == 2;
    }

//    @Test
//    void getAvailable() {
//        assert this.sessionController.getAvailable().getSessions().size() == 6;
//        for(int i=0; i<50; i++){
//            this.ticketService.addTicket(new Ticket(-1, new BigDecimal(100), i+20, new Date(System.currentTimeMillis()), "Vlad", 1));
//        }
//        assert this.sessionController.getAvailable().getSessions().size() == 5;
//    }

    @Test
    void addSession() {
        ArrayList<PaperDto> paperDtos = new ArrayList<>();
        this.sessionController.addSession(new CreateSessionRequest(2, 2, "12/06/2020 12:00:00", paperDtos));
        assert this.sessionController.getAllSessions().getSessions().size() == 6;
    }
}