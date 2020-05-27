package ro.ubb.project.web.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthorControllerTest {

    @Autowired
    private AuthorController authorController;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void register() {
        //this.authorController.register();
    }

    @Test
    void getAuthors() {
        assert this.authorController.getAuthors(1).size() == 1;
    }

    @Test
    void getAuthor() {
    }
}