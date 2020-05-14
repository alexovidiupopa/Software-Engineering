package ro.ubb.project.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.project.core.service.SessionService;
import ro.ubb.project.web.converter.SessionConverter;
import ro.ubb.project.web.dto.SessionDto;
import ro.ubb.project.web.response.SessionsResponse;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SessionConverter converter;

    @RequestMapping(value = "/getSessionWithId/{id}", method = RequestMethod.GET)
    SessionDto getSessionWithId(@PathVariable Integer id) {
        return converter.modelToDto(sessionService.getSessionWithId(id));
    }

    @RequestMapping(value = "/getAllSessions", method = RequestMethod.GET)
    SessionsResponse getAllSessions() {
        return new SessionsResponse((ArrayList<SessionDto>) converter.convertModelsToDtos(sessionService.getAllSessions()));
    }
}
