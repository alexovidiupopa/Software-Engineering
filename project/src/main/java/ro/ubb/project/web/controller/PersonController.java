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
import java.util.Set;

@RestController
@RequestMapping("/api")
public class PersonController {

    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonConverter converter;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Set<PersonDto> getPersons(){
        List<Person> persons = personService.getAllPersons();
        return (Set<PersonDto>) converter.convertModelsToDtos(persons);
    }

}
