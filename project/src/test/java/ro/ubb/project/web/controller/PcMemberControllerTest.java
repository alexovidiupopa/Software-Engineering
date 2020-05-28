package ro.ubb.project.web.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.ubb.project.core.model.Chair;
import ro.ubb.project.core.service.ChairService;
import ro.ubb.project.web.request.RegisterRequest;

@SpringBootTest
class PcMemberControllerTest {

    @Autowired
    private PcMemberController pcMemberController;

    @Autowired
    private ChairService chairService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllPcMembers() {
        assert this.pcMemberController.getAllPcMembers().getPcMember().size() == 5;
        assert this.pcMemberController.getAllPcMembers().getPcMember().get(4).getUid() == 1;
        assert this.pcMemberController.getAllPcMembers().getPcMember().get(4).getPcid() == 5;
    }

    @Test
    void getPcMemberById() {
        assert this.pcMemberController.getPcMemberById(3).getPcMember().getPcid() == 2;
        assert this.pcMemberController.getPcMemberById(3).getPcMember().getUid() == 3;
    }

//    @Test
//    void pcToChair() {
//        this.pcMemberController.pcToChair(3);
//        assert this.chairService.isChair(3);
//        this.chairService.deleteChair(new Chair(3, 3));
//    }

//    @Test
//    void register() {
//        this.pcMemberController.register(new RegisterRequest());
//    }
}