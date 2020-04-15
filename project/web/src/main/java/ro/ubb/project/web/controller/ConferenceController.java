package ro.ubb.project.web.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.project.web.request.ConferenceRequest;

@RestController
public class ConferenceController {

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createConference(@RequestBody ConferenceRequest conference){
        System.out.println(conference.getFirstPhaseDeadline());
    }
}
