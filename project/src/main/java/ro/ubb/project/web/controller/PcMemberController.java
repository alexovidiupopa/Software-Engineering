package ro.ubb.project.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.project.core.model.PcMember;
import ro.ubb.project.core.service.ChairService;
import ro.ubb.project.core.service.PcMemberService;
import ro.ubb.project.core.service.PersonService;
import ro.ubb.project.core.utils.EmailSender;
import ro.ubb.project.web.converter.ChairConverter;
import ro.ubb.project.web.converter.PcMemberConverter;
import ro.ubb.project.web.converter.PersonConverter;
import ro.ubb.project.web.dto.ChairDto;
import ro.ubb.project.web.dto.PcMemberDto;
import ro.ubb.project.web.dto.PersonDto;
import ro.ubb.project.web.request.RegisterRequest;
import ro.ubb.project.web.response.MessageResponse;
import ro.ubb.project.web.response.PcMemberResponse;
import ro.ubb.project.web.response.PcMembersResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pc")
public class PcMemberController {

    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @Autowired
    private PcMemberService pcMemberService;

    @Autowired
    private ChairService chairService;

    @Autowired
    private PersonConverter personConverter;

    @Autowired
    private PcMemberConverter pcMemberConverter;

    @Autowired
    private ChairConverter chairConverter;

    @RequestMapping(value = "/getAllPcMembers", method = RequestMethod.GET)
    public PcMembersResponse getAllPcMembers() {
        List<PcMember> pcMembers = this.pcMemberService.getAllPcMembers();
        ArrayList<PcMemberDto> pcMemberDtos = new ArrayList<>();
        PcMemberConverter pcMemberConverter = new PcMemberConverter();
        pcMembers.forEach(pc -> pcMemberDtos.add(pcMemberConverter.modelToDto(pc)));
        return new PcMembersResponse(pcMemberDtos);
    }

    @RequestMapping(value = "/getPcMemberById/{id}", method = RequestMethod.GET)
    public PcMemberResponse getPcMemberById(@PathVariable Integer id) {
        int pcId = this.pcMemberService.getPcIdByUid(id);
        //Optional<PcMember> pcMember = this.pcMemberService.getPcMemberById(pcId);
        return new PcMemberResponse(new PcMemberDto(pcId, id));
    }

    @RequestMapping(value = "/pcToChair/{id}", method = RequestMethod.PUT)
    public MessageResponse pcToChair(@PathVariable Integer id) {
        Optional<PcMember> pcMember = this.pcMemberService.getPcMemberById(this.pcMemberService.getPcIdByUid(id));
        System.out.println(pcMember.get().getUid());
        if (pcMember.isPresent()) {
            pcMemberService.deletePcMember(pcMember.get());
            chairService.addChair(chairConverter.dtoToModel(
                    ChairDto.builder()
                            .uid(id)
                            .build()));
            return new MessageResponse("true");
        } else {
            return new MessageResponse("false");
        }
    }

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
        pcMemberService.addPcMember(pcMemberConverter.dtoToModel(
                PcMemberDto.builder()
                        .uid(personService.getPersonByUserName(registerRequest.getUsername()).getUid())
                        .build()
        ));
        EmailSender.send(EmailSender.ORIGIN_EMAIL, registerRequest.getEmail(), EmailSender.WELCOME_SUBJECT, EmailSender.LOGIN_LINK);
        return new MessageResponse("true");
    }
}
