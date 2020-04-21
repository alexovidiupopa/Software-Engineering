package ro.ubb.project.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.project.core.service.Scheduler;
import ro.ubb.project.web.request.ConferenceRequest;
import ro.ubb.project.web.response.MessageResponse;

@RestController
public class ConferenceController {

    private static final Logger log = LoggerFactory.getLogger(ConferenceController.class);

    @Autowired
    private Scheduler scheduler;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public MessageResponse createConference(@RequestBody ConferenceRequest conference){
        log.trace("received conference creation request={}",conference);
        scheduler.setConferenceName(conference.getName());
        scheduler.setFirstPhaseDeadline(conference.getFirstPhaseDeadline());
        scheduler.setSecondPhaseDeadline(conference.getSecondPhaseDeadline());
        scheduler.setThirdPhaseDeadline(conference.getThirdPhaseDeadline());
        log.trace("created scheduler={}",scheduler);
        return new MessageResponse("success");
    }
}
