package ro.ubb.project.core.service;

import ro.ubb.project.core.model.Session;

import java.util.List;

public interface SessionService {

    List<Session> getAllSessions();

    void addSession(Session session);

    void deleteSession(Session session);

    void updateSession(Session session);

    Session getSessionWithId(Integer id);

    int noAvailableSeats(Integer id);
}
