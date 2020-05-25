package ro.ubb.project.web.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.ubb.project.web.request.MessageRequest;

@SpringBootTest
class ChairControllerTest {

    @Autowired
    private ChairController chairController;

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    void invitePc() {
        assert(this.chairController.invitePc(new MessageRequest("Test invite PC")).getMessage().equals("true"));
    }

    @Test
    void getAllChairs() {
        assert(this.chairController.getAllChairs().getChairs().size() == 2);
        assert(this.chairController.getAllChairs().getChairs().get(0).getCid() == 1);
        assert(this.chairController.getAllChairs().getChairs().get(0).getUid() == 4);
    }
}