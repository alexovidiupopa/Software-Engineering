package ro.ubb.project.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.project.core.model.Session;
import ro.ubb.project.web.dto.SessionDto;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class SessionConverter extends AbstractConverter<Session, SessionDto> {
    @Override
    public Session dtoToModel(SessionDto sessionDto) {
        return Session.builder()
                .sid(sessionDto.getSid())
                .time(sessionDto.getTime())
                .rid(sessionDto.getRid())
                .supervisor(sessionDto.getSupervisor())
                .build();
    }

    @Override
    public SessionDto modelToDto(Session session) {
        return SessionDto.builder()
                .sid(session.getSid())
                .time(session.getTime())
                .rid(session.getRid())
                .price(ThreadLocalRandom.current().nextInt(10,50 + 1))
                .supervisor(session.getSupervisor())
                .build();
    }
}
