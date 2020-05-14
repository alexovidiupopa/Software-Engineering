package ro.ubb.project.web.controller;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.project.core.service.Scheduler;
import ro.ubb.project.web.request.ConferenceRequest;
import ro.ubb.project.web.request.MessageRequest;
import ro.ubb.project.web.response.MessageResponse;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@RestController
@RequestMapping("/api/conference")
public class ConferenceController {

    private static final Logger log = LoggerFactory.getLogger(ConferenceController.class);

    @Autowired
    private Scheduler scheduler;

    @RequestMapping(value = "/exists", method = RequestMethod.GET)
    Boolean conferenceExists() {
        return scheduler.getConferenceName() != null;
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public MessageResponse createConference(@RequestBody ConferenceRequest conference) {
        System.out.println(conference);
        log.trace("received conference creation request={}", conference);
        DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/DD/YYYY HH:mm:ss");
        scheduler.setConferenceName(conference.getConferenceName());
        scheduler.setFirstPhaseDeadline(dtf.parseDateTime(conference.getFirstPhaseDeadline()).toString());
        scheduler.setSecondPhaseDeadline(dtf.parseDateTime(conference.getSecondPhaseDeadline()).toString());
        scheduler.setThirdPhaseDeadline(dtf.parseDateTime(conference.getThirdPhaseDeadline()).toString());

        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(new File("src\\main\\resources\\conference.txt")));
            bf.write("name=" + conference.getConferenceName());
            bf.write("preliminaryPhaseDeadline=" + DateTime.parse(conference.getPreliminaryPhaseDeadline(), dtf).toString());
            bf.write("firstPhaseDeadline=" + DateTime.parse(conference.getFirstPhaseDeadline(), dtf).toString());
            bf.write("secondPhaseDeadline=" + DateTime.parse(conference.getSecondPhaseDeadline(), dtf).toString());
            bf.write("thirdPhaseDeadline=" + DateTime.parse(conference.getThirdPhaseDeadline(), dtf).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.trace("created scheduler={}", scheduler);
        return new MessageResponse("success");
    }

    @RequestMapping(value = "/getCurrentPhase", method = RequestMethod.GET)
    public MessageResponse getCurrentPhase() {
        return new MessageResponse(scheduler.getCurrentPhase());
    }

    @RequestMapping(value = "/updateDeadline", method = RequestMethod.POST)
    public MessageResponse updateDeadline(@RequestBody MessageRequest newDeadline) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/DD/YYYY HH:mm:ss");
        scheduler.updateCurrentDeadline(dtf.parseDateTime(newDeadline.getMessage()));
        return new MessageResponse("success");
    }
}
