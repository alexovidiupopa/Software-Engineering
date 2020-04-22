package ro.ubb.project.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.project.core.service.PersonService;
import ro.ubb.project.core.service.PersonServiceImpl;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;


}
