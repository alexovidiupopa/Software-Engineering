package ro.ubb.project.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.project.core.model.PcMember;
import ro.ubb.project.core.service.ChairService;
import ro.ubb.project.core.service.PcMemberService;
import ro.ubb.project.core.service.PersonService;
import ro.ubb.project.web.converter.ChairConverter;
import ro.ubb.project.web.converter.PcMemberConverter;
import ro.ubb.project.web.converter.PersonConverter;
import ro.ubb.project.web.dto.ChairDto;
import ro.ubb.project.web.dto.PcMemberDto;
import ro.ubb.project.web.request.GetPcMemberByIdRequest;
import ro.ubb.project.web.request.PcToChairRequest;
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
    public PcMembersResponse getAllPcMembers(){
        List<PcMember> pcMembers = this.pcMemberService.getAllPcMembers();
        ArrayList<PcMemberDto> pcMemberDtos = new ArrayList<>();
        PcMemberConverter pcMemberConverter = new PcMemberConverter();
        pcMembers.forEach(pc -> pcMemberDtos.add(pcMemberConverter.modelToDto(pc)));
        return new PcMembersResponse(pcMemberDtos);
    }

    @RequestMapping(value = "/getPcMemberById", method = RequestMethod.GET)
    public PcMemberResponse getPcMemberById(@RequestBody GetPcMemberByIdRequest getPcMemberByIdRequest){
        Optional<PcMember> pcMember = this.pcMemberService.getPcMemberById(getPcMemberByIdRequest.getPcid());
        return pcMember.map(member -> new PcMemberResponse(new PcMemberConverter().modelToDto(member))).orElseGet(PcMemberResponse::new);
    }

    @RequestMapping(value = "/pcToChair", method = RequestMethod.POST)
    public MessageResponse pcToChair(@RequestBody PcToChairRequest pcToChairRequest){
        Optional<PcMember> pcMember = this.pcMemberService.getPcMemberById(pcToChairRequest.getPcid());
        if(pcMember.isPresent()) {
            chairService.addChair(chairConverter.dtoToModel(
                    ChairDto.builder()
                            .uid(pcMember.get().getUid())
                            .build()));
            return new MessageResponse("success");
        }
        else {
            return new MessageResponse("error");
        }
    }
}