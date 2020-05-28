package ro.ubb.project.web.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.project.core.model.Person;
import ro.ubb.project.core.service.AuthorService;
import ro.ubb.project.core.service.ChairService;
import ro.ubb.project.core.service.PcMemberService;
import ro.ubb.project.core.service.PersonService;
import ro.ubb.project.web.request.LoginRequest;
import ro.ubb.project.web.response.MessageResponse;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private PersonService personService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private ChairService chairService;

    @Autowired
    private PcMemberService pcMemberService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    MessageResponse login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        Algorithm algorithm = Algorithm.HMAC256("secret");
        try {
            Person person = personService.getPersonByUserName(username);
            if (!person.getPassword().equals(password))
                return new MessageResponse(JWT.create()
                        .withIssuer("admin")
                        .withClaim("success", false)
                        .withClaim("type", "")
                        .withClaim("uid", 0)
                        .withClaim("url","")
                        .sign(algorithm));
            int uid = person.getUid();
            if (chairService.isChair(uid))
                return new MessageResponse(JWT.create()
                        .withIssuer("admin")
                        .withClaim("success", true)
                        .withClaim("type", "chair")
                        .withClaim("uid", uid)
                        .withClaim("url","/chair-home")
                        .withClaim("firstname",person.getFirstname())
                        .withClaim("lastname",person.getLastname())
                        .withClaim("username",person.getUsername())
                        .sign(algorithm));
            if (pcMemberService.isPcMember(uid))
                return new MessageResponse(JWT.create()
                        .withIssuer("admin")
                        .withClaim("success", true)
                        .withClaim("type", "pc")
                        .withClaim("uid", uid)
                        .withClaim("url","/pc-home")
                        .withClaim("firstname",person.getFirstname())
                        .withClaim("lastname",person.getLastname())
                        .withClaim("username",person.getUsername())
                        .sign(algorithm));
            if (authorService.isAuthor(uid))
                return new MessageResponse(JWT.create()
                        .withIssuer("admin")
                        .withClaim("success", true)
                        .withClaim("type", "author")
                        .withClaim("uid", uid)
                        .withClaim("url","/author-home")
                        .withClaim("firstname",person.getFirstname())
                        .withClaim("lastname",person.getLastname())
                        .withClaim("username",person.getUsername())
                        .sign(algorithm));
            return new MessageResponse(JWT.create()
                    .withIssuer("admin")
                    .withClaim("success", false)
                    .withClaim("type", "")
                    .withClaim("uid", 0)
                    .withClaim("url","/")
                    .sign(algorithm));
        } catch (RuntimeException e) {
            return new MessageResponse(JWT.create()
                    .withIssuer("admin")
                    .withClaim("success", false)
                    .withClaim("type", "")
                    .withClaim("uid", 0)
                    .withClaim("url","/")
                    .sign(algorithm));
        }

    }
}
