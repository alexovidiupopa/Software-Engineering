package ro.ubb.project.web.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.project.core.service.AuthorService;
import ro.ubb.project.core.service.PersonService;

import static org.junit.jupiter.api.Assertions.*;

class AuthorControllerTest {

    @Autowired
    private AuthorController authorController;

    @Autowired
    private AuthorService authorService;

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