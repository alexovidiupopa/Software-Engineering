package ro.ubb.project.web.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.ubb.project.web.dto.PaymentDataDto;
import ro.ubb.project.web.dto.TicketDto;
import ro.ubb.project.web.dto.TicketsDto;
import ro.ubb.project.web.request.PayCartRequest;
import ro.ubb.project.web.request.TicketPurchaseRequest;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;

@SpringBootTest
class TicketControllerTest {

    @Autowired
    private TicketController ticketController;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void payCart() {
        ArrayList<TicketDto> l = new ArrayList<>();
        l.add(new TicketDto(1, BigDecimal.valueOf(100), 4, Date.valueOf("2020-06-07"), "Vlad", 3));
        assert this.ticketController.payCart(PayCartRequest.builder()
                .tickets(l).email("pvie2601@scs.ubbcluj.ro").build())
                .getMessage().equals("true");
    }

    @Test
    void buy() {
        assert this.ticketController.buy(new TicketPurchaseRequest(
                Collections.singletonList(new TicketsDto(1, BigDecimal.valueOf(100),
                        4, Date.valueOf("2020-06-07"), "Vlad", Collections.singletonList(3))),
                new PaymentDataDto("123456781234", "26/09/2021", "Vlad Pop", "123"),
                "pvie2601@scs.ubbcluj.ro"))
                .getMessage().equals("true");
    }
}