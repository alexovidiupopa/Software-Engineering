package ro.ubb.project.web.controller;

import com.auth0.jwt.JWT;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.ubb.project.web.request.LoginRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginControllerTest {

    @Autowired
    private LoginController loginController;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void login() {
        assert JWT.decode(this.loginController.login(new LoginRequest("PopAnca", "1qw2")).getMessage()).getClaim("success").asBoolean();
        assert JWT.decode(this.loginController.login(new LoginRequest("Daniel2019", "myPasswd")).getMessage()).getClaim("success").asBoolean();
        assertFalse(JWT.decode(this.loginController.login(new LoginRequest("", "")).getMessage()).getClaim("success").asBoolean());
        assertFalse(JWT.decode(this.loginController.login(new LoginRequest("Mark420", "")).getMessage()).getClaim("success").asBoolean());
        assertFalse(JWT.decode(this.loginController.login(new LoginRequest("Mark420", "wrongPassword")).getMessage()).getClaim("success").asBoolean());
    }
}