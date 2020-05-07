package ro.ubb.project.web.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.project.web.request.MessageRequest;
import ro.ubb.project.web.response.MessageResponse;
import ro.ubb.project.web.utils.EmailSender;

@RestController
@RequestMapping("/api/chair")
public class ChairController {
    @RequestMapping(value = "/invitePc",method = RequestMethod.POST)
    MessageResponse invitePc(@RequestBody MessageRequest emailRequest){
        EmailSender.send(EmailSender.ORIGIN_EMAIL,emailRequest.getMessage(),EmailSender.JOIN_SUBJECT, EmailSender.REGISTER_LINK);
        return new MessageResponse("success");
    }
}
