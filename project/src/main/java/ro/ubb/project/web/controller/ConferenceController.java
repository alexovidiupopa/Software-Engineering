package ro.ubb.project.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.project.core.service.Scheduler;
import ro.ubb.project.web.request.ConferenceRequest;
import ro.ubb.project.web.response.MessageResponse;

@RestController
@RequestMapping("/api")
public class ConferenceController {

    private static final Logger log = LoggerFactory.getLogger(ConferenceController.class);

    @Autowired
    private Scheduler scheduler;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public MessageResponse createConference(@RequestBody ConferenceRequest conference){
        System.out.println(conference);
        log.trace("received conference creation request={}",conference);
        //DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/YYYY HH:mm:ss");
        scheduler.setConferenceName(conference.getConferenceName());
        /*scheduler.setFirstPhaseDeadline(dtf.parseDateTime(conference.getFirstPhaseDeadline()));
        scheduler.setSecondPhaseDeadline(dtf.parseDateTime(conference.getSecondPhaseDeadline()));
        scheduler.setThirdPhaseDeadline(dtf.parseDateTime(conference.getThirdPhaseDeadline()));*/

        log.trace("created scheduler={}",scheduler);
        return new MessageResponse("success");
    }
}
