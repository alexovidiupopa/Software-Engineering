package ro.ubb.project.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.project.core.service.ChairService;
import ro.ubb.project.core.utils.EmailSender;
import ro.ubb.project.web.converter.ChairConverter;
import ro.ubb.project.web.dto.ChairDto;
import ro.ubb.project.web.request.MessageRequest;
import ro.ubb.project.web.response.ChairsResponse;
import ro.ubb.project.web.response.MessageResponse;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/chair")
public class ChairController {

    @Autowired
    private ChairService chairService;

    @Autowired
    private ChairConverter converter;

    @RequestMapping(value = "/invitePc", method = RequestMethod.POST)
    MessageResponse invitePc(@RequestBody MessageRequest emailRequest) {
        EmailSender.send(EmailSender.ORIGIN_EMAIL, emailRequest.getMessage(), EmailSender.JOIN_SUBJECT, EmailSender.REGISTER_LINK);
        return new MessageResponse("true");
    }

    @RequestMapping(value="/getAllChairs", method = RequestMethod.GET)
    ChairsResponse getAllChairs(){
        return new ChairsResponse((ArrayList<ChairDto>) converter.convertModelsToDtos(chairService.getAllChairs()));
    }
}
