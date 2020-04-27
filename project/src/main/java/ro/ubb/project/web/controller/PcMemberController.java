package ro.ubb.project.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.project.core.service.PcMemberService;
import ro.ubb.project.core.service.PersonService;
import ro.ubb.project.web.converter.PcMemberConverter;
import ro.ubb.project.web.converter.PersonConverter;
import ro.ubb.project.web.dto.PcMemberDto;
import ro.ubb.project.web.dto.PersonDto;
import ro.ubb.project.web.request.RegisterRequest;
import ro.ubb.project.web.response.MessageResponse;
import ro.ubb.project.web.utils.EmailSender;

@RestController
@RequestMapping("/api/pc")
public class PcMemberController {

    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @Autowired
    private PcMemberService pcMemberService;

    @Autowired
    private PersonConverter personConverter;

    @Autowired
    private PcMemberConverter pcMemberConverter;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public MessageResponse register(@RequestBody RegisterRequest registerRequest){
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
        pcMemberService.addPcMember(pcMemberConverter.dtoToModel(
                PcMemberDto.builder()
                        .uid(personService.getPersonByUserName(registerRequest.getUsername()).getUid())
                        .build()
        ));
        EmailSender.send(EmailSender.ORIGIN_EMAIL, registerRequest.getEmail(),EmailSender.WELCOME_MSG, "http://localhost:4200/api/login");
        return new MessageResponse("success");
    }
}
