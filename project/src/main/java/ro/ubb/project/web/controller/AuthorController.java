package ro.ubb.project.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.project.core.model.Author;
import ro.ubb.project.core.model.Paper;
import ro.ubb.project.core.service.AuthorService;
import ro.ubb.project.core.service.PaperService;
import ro.ubb.project.core.service.PersonService;
import ro.ubb.project.core.utils.EmailSender;
import ro.ubb.project.web.converter.AuthorConverter;
import ro.ubb.project.web.converter.PersonConverter;
import ro.ubb.project.web.dto.AuthorDto;
import ro.ubb.project.web.dto.PersonDto;
import ro.ubb.project.web.request.RegisterRequest;
import ro.ubb.project.web.response.MessageResponse;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private PaperService paperService;

    @Autowired
    private PersonConverter personConverter;

    @Autowired
    private AuthorConverter authorConverter;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public MessageResponse register(@RequestBody RegisterRequest registerRequest) {
        System.out.println(registerRequest);
        personService.addPerson(personConverter.dtoToModel(
                PersonDto.builder()
                        .username(registerRequest.getUsername())
                        .password(registerRequest.getPassword())
                        .email(registerRequest.getEmail())
                        .affiliation(registerRequest.getAffiliation())
                        .firstname(registerRequest.getFirstname())
                        .lastname(registerRequest.getLastname())
                        .website(registerRequest.getWebsite())
                        .phonenumber(registerRequest.getPhonenumber())
                        .academicrank(registerRequest.getAcademicrank())
                        .build()
        ));
        authorService.addAuthor(authorConverter.dtoToModel(
                AuthorDto.builder()
                        .uid(personService.getPersonByUserName(registerRequest.getUsername()).getUid())
                        .build()
        ));
        EmailSender.send(EmailSender.ORIGIN_EMAIL, registerRequest.getEmail(), EmailSender.WELCOME_SUBJECT, EmailSender.LOGIN_LINK);
        return new MessageResponse("true");
    }

    @RequestMapping(value = "/get-for-session/{id}", method = RequestMethod.GET)
    Collection<AuthorDto> getAuthors(@PathVariable Integer id) {
        List<Author> authors = paperService.getAllPapers().stream()
                .filter(p -> p.getSession() == id)
                .map(Paper::getAid)
                .map(aid -> authorService.getAuthorById(aid))
                .collect(Collectors.toList());
        return authorConverter.convertModelsToDtos(authors);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    PersonDto getAuthor(@PathVariable Integer id) {
        return personConverter.modelToDto(
                personService.getPersonById((authorService.getAuthorById(id)).getUid())
        );
    }
}
