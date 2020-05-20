package ro.ubb.project.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.project.core.model.Paper;
import ro.ubb.project.core.model.Session;
import ro.ubb.project.core.service.PaperService;
import ro.ubb.project.core.service.SessionService;
import ro.ubb.project.web.converter.PaperConverter;
import ro.ubb.project.web.converter.SessionConverter;
import ro.ubb.project.web.dto.SessionDto;
import ro.ubb.project.web.request.CreateSessionRequest;
import ro.ubb.project.web.response.MessageResponse;
import ro.ubb.project.web.response.SessionsResponse;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SessionConverter converter;


    @Autowired
    private PaperService paperService;

    @Autowired
    private PaperConverter paperConverter;

    @RequestMapping(value = "/getSessionWithId/{id}", method = RequestMethod.GET)
    SessionDto getSessionWithId(@PathVariable Integer id) {
        return converter.modelToDto(sessionService.getSessionWithId(id));
    }

    @RequestMapping(value = "/getAllSessions", method = RequestMethod.GET)
    SessionsResponse getAllSessions() {
        return new SessionsResponse((ArrayList<SessionDto>) converter.convertModelsToDtos(sessionService.getAllSessions()));
    }

    @RequestMapping(value = "/available", method = RequestMethod.GET)
    SessionsResponse getAvailable() {
        List<Session> sessions = this.sessionService.getAllSessions()
                .stream()
                .filter(s -> this.sessionService.noAvailableSeats(s.getSid()) > 0)
                .collect(Collectors.toList());
        return new SessionsResponse((ArrayList<SessionDto>) converter.convertModelsToDtos(sessions));
    }

    @RequestMapping(value = "/addSession", method = RequestMethod.POST)
    MessageResponse addSession(@RequestBody CreateSessionRequest createSessionRequest){
        try{
            System.out.println(createSessionRequest.getTime());
            sessionService.addSession(
                    Session.builder()
                            .supervisor(createSessionRequest.getCid())
                            .rid(createSessionRequest.getRid())
                            .time(Time.valueOf(createSessionRequest.getTime()+":00"))
                            .build()
            );


            int sid = sessionService.getAllSessions()
                    .stream()
                    .filter(session -> session.getSupervisor()==createSessionRequest.getCid() && session.getRid()==createSessionRequest.getRid())
                    .map(Session::getSid)
                    .findAny()
                    .orElseThrow(RuntimeException::new);

            System.out.println(sid);

            System.out.println(createSessionRequest.getPapers());

            createSessionRequest.getPapers()
                    .forEach(paperDto -> {
                        Paper paper = paperConverter.dtoToModel(paperDto);
                        paperService.deletePaper(paper);
                        paper.setSession(sid);
                        paperService.addPaper(paper);
                    });

            // for each paper, bind it to the created session
            return new MessageResponse("true");
        }
        catch (RuntimeException re){
            System.out.println(re.getMessage());
            return new MessageResponse("false");
        }
    }
}
