package ro.ubb.project.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.project.core.model.Session;
import ro.ubb.project.core.repository.SessionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public List<Session> getAllSessions() {
        return this.sessionRepository.findAll();
    }

    @Override
    public void addSession(Session session) {
        this.sessionRepository.save(session);
    }

    @Override
    public void deleteSession(Session session) {
        this.sessionRepository.delete(session);
    }

    @Override
    public void updateSession(Session session) {
        Optional<Session> toUpdate = this.sessionRepository.findById(session.getSid());
        if (toUpdate.isPresent()) {
            Session s = toUpdate.get();
            s.setTime(session.getTime());
            this.sessionRepository.save(s);
        } else {
            throw new RuntimeException("No assignment found");
        }
    }

    @Override
    public Session getSessionWithId(Integer id) {
        Optional<Session> paper = this.sessionRepository.findById(id);
        if (paper.isPresent())
            return paper.get();
        else
            throw new RuntimeException("No session found");
    }
}
