package ro.ubb.project.web.controller;

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
import ro.ubb.project.web.response.LoginResponse;

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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    LoginResponse login(@RequestBody LoginRequest loginRequest){
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        try{
            Person person = personService.getPersonByUserName(username);
            if (!person.getPassword().equals(password))
                return new LoginResponse(false, "");

            int uid = person.getUid();
            if (chairService.isChair(uid))
                return new LoginResponse(true,"chair");

            if (authorService.isAuthor(uid))
                return new LoginResponse(true,"author");

            if (pcMemberService.isPcMember(uid))
                return new LoginResponse(true,"pc");

            return new LoginResponse(false, "");
        }
        catch (RuntimeException e) {
            return new LoginResponse(false, "");
        }

    }
}
