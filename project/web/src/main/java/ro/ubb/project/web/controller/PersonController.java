package ro.ubb.project.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.project.core.model.Person;
import ro.ubb.project.core.service.PersonService;
import ro.ubb.project.web.converter.PersonConverter;
import ro.ubb.project.web.dto.PersonDto;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonConverter converter;

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public List<PersonDto> getPersons(){

        List<Person> persons = personService.getAllPersons();

        return (List<PersonDto>) converter.convertModelsToDtos(persons);
    }

    @RequestMapping(value="/persons", method=RequestMethod.PUT)
    public String login(){
        return "ok";
    }
}
