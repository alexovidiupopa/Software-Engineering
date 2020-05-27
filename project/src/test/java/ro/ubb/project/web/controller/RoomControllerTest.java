package ro.ubb.project.web.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.ubb.project.web.dto.RoomDto;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoomControllerTest {

    @Autowired
    private RoomController roomController;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(1)
    void getAllRooms() {
        assert this.roomController.getAllRooms().getRooms().size() == 3;
        assert this.roomController.getAllRooms().getRooms().get(0).getCapacity() == 50;
        assert this.roomController.getAllRooms().getRooms().get(1).getRid() == 2;
        assert this.roomController.getAllRooms().getRooms().get(2).getCapacity() == 200;
    }

    @Test
    @Order(2)
    void addRoom() {
        this.roomController.addRoom(new RoomDto(7, 100));
        assert this.roomController.getAllRooms().getRooms().size() == 4;
        assert this.roomController.getAllRooms().getRooms().get(3).getCapacity() == 100;
    }

    @Test
    @Order(3)
    void delete() {
        this.roomController.delete(7);
        assert this.roomController.getAllRooms().getRooms().size() == 3;
    }
}