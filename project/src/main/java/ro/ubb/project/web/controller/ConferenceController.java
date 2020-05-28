package ro.ubb.project.web.controller;

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

import java.io.*;

@RestController
@RequestMapping("/api/conference")
public class ConferenceController {

    private static final Logger log = LoggerFactory.getLogger(ConferenceController.class);

    @Autowired
    private Scheduler scheduler;

    @RequestMapping(value = "/exists", method = RequestMethod.GET)
    MessageResponse conferenceExists() {
        if (getCurrentPhase().getMessage().equals("false"))
            return new MessageResponse("false");
        return new MessageResponse("true");
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public MessageResponse createConference(@RequestBody ConferenceRequest conference) {
        System.out.println(conference);
        log.trace("received conference creation request={}", conference);
        DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/DD/YYYY HH:mm:ss");
        scheduler.setConferenceName(conference.getConferenceName());
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(new File("src\\main\\resources\\conference.txt")));
            bf.write( conference.getConferenceName() + "\n");
            bf.write( conference.getPreliminaryPhaseDeadline() + "\n");
            bf.write(conference.getFirstPhaseDeadline() + "\n");
            bf.write(conference.getSecondPhaseDeadline() + "\n");
            bf.write(conference.getThirdPhaseDeadline() + "\n");
            bf.close();
            log.trace("created scheduler={}", scheduler);
            System.out.println(scheduler);
            return new MessageResponse("true");
        } catch (IOException e) {
            e.printStackTrace();
            return new MessageResponse("false");
        }


    }

    @RequestMapping(value = "/getCurrentPhase", method = RequestMethod.GET)
    public MessageResponse getCurrentPhase() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("src\\main\\resources\\conference.txt")));
            DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/DD/YYYY HH:mm:ss");
            String name = br.readLine();
            String preliminary = br.readLine();
            String first = br.readLine();
            String second = br.readLine();
            String third = br.readLine();
            scheduler.setConferenceName(name);
            scheduler.setPreliminaryPhaseDeadline(preliminary);
            scheduler.setFirstPhaseDeadline(first);
            scheduler.setSecondPhaseDeadline(second);
            scheduler.setThirdPhaseDeadline(third);
            System.out.println(scheduler);
            return new MessageResponse(scheduler.getCurrentPhase());
        } catch (IOException e) {
            e.printStackTrace();
            return new MessageResponse("false");
        }


    }

    @RequestMapping(value = "/updateDeadline", method = RequestMethod.POST)
    public MessageResponse updateDeadline(@RequestBody MessageRequest newDeadline) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/DD/YYYY HH:mm:ss");
        scheduler.updateCurrentDeadline(dtf.parseDateTime(newDeadline.getMessage()));
        return new MessageResponse("true");
    }
}
