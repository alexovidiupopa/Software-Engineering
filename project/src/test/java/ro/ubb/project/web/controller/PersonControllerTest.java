package ro.ubb.project.web.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PersonControllerTest {

    @Autowired
    private PersonController personController;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPersons() {
        assert this.personController.getPersons().size() == 11;
        this.personController.getPersons().forEach(p -> {assert p.getPhonenumber().length() == 10; });
    }

    @Test
    void getUserById() {
        assert this.personController.getUserById(1).getUsername().equals("Mark420");
        assert this.personController.getUserById(5).getFirstname().equals("Andrew");
    }
}