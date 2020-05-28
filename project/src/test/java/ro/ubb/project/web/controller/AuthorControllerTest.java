package ro.ubb.project.web.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.ubb.project.core.service.AuthorService;
import ro.ubb.project.core.service.PersonService;
import ro.ubb.project.web.request.RegisterRequest;

@SpringBootTest
class AuthorControllerTest {

    @Autowired
    private AuthorController authorController;

    @Autowired
    private PersonService personService;

    @Autowired
    private AuthorService authorService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

//    @Test
//    void register() {
//        this.authorController.register(new RegisterRequest("test", "test", "test", "test", "test", "test", "test", "test", "test"));
//        assert this.personService.getPersonByUserName("test") != null;
//        assert this.authorService.getAuthorByUid(this.personService.getPersonByUserName("test").getUid()) != null;
//        this.authorService.deleteAuthor(this.authorService.getAuthorByUid(this.personService.getPersonByUserName("test").getUid()));
//        this.personService.deletePerson(this.personService.getPersonByUserName("test"));
//    }

    @Test
    void getAuthors() {
        assert this.authorController.getAuthors(3).size() == 3;
    }

    @Test
    void getAuthor() {
        assert this.authorController.getAuthor(1).getUid() == 1;
    }
}