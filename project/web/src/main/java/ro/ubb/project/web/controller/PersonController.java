package ro.ubb.project.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.project.core.model.Person;
import ro.ubb.project.core.service.PersonService;
import ro.ubb.project.web.converter.PersonConverter;
import ro.ubb.project.web.dto.PersonDto;
import ro.ubb.project.web.request.LoginRequest;
import ro.ubb.project.web.request.RegisterRequest;
import ro.ubb.project.web.response.LoginResponse;
import ro.ubb.project.web.response.MessageResponse;
import ro.ubb.project.web.utils.EmailSender;

import java.util.List;

@RestController
public class PersonController {

    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonConverter converter;

    @RequestMapping(value = "/")
    private String index(){
        System.out.println("here");
        return "";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<PersonDto> getPersons(){
        List<Person> persons = personService.getAllPersons();
        return (List<PersonDto>) converter.convertModelsToDtos(persons);
    }

    @RequestMapping(value="/login", method=RequestMethod.PUT)
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        //TODO implement this...
        return new LoginResponse();
    }

    @RequestMapping(value="/register", method=RequestMethod.PUT)
    public MessageResponse register(@RequestBody RegisterRequest registerRequest){
        personService.addPerson(converter.dtoToModel(
                PersonDto.builder()
                        .username(registerRequest.getUsername())
                        .password(registerRequest.getPassword())
                        .email(registerRequest.getEmail())
                        .affiliation(registerRequest.getAffiliation())
                        .firstname(registerRequest.getFirstname())
                        .lastname(registerRequest.getLastname())
                        .website(registerRequest.getWebsite())
                        .phonenumber(registerRequest.getPhonenumber())
                        .build()
        ));
        EmailSender.send("noreply@cms.ro", registerRequest.getEmail(),"Welcome to CMS!", "Dummy message");
        return new MessageResponse("user successfully registered");
    }
}
