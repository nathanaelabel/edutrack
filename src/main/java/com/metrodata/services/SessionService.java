package com.metrodata.services;

import com.metrodata.entities.Session;
import com.metrodata.entities.models.ResponseData;
import com.metrodata.entities.models.SessionData;
import com.metrodata.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final EventService eventService;
    private final SessionRepository sessionRepository;

    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    public Session getSessionById(Long id) {
        return sessionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Session with ID: " + id + " not found"));
    }

    public ResponseData<Session> insertSession(SessionData sessionData) {

        // contoh function yang menggunakan DTO

        try {
            Session session = new Session();
            session.setName(sessionData.getName());
            session.setStartTime(sessionData.getStartTime());
            session.setEndTime(sessionData.getEndTime());
            session.setDescription(sessionData.getDescription());
            session.setEvent(eventService.getEventById(sessionData.getEventId()));
            Session newSession = sessionRepository.save(session);
            return new ResponseData<>(newSession, "Session created successfully");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        // contoh function yang tidak menggunakan DTO

        // try {
        //      Session newSession = sessionRepository.save(session);
        //      return new ResponseData<>(newSession, "Session created successfully");
        //  } catch (Exception e) {
        //      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        //  }
    }

    public Session updateSession(long id, Session sessionData) {
        Session session = getSessionById(id);
        session.setName(sessionData.getName());
        session.setStartTime(sessionData.getStartTime());
        session.setEndTime(sessionData.getEndTime());
        session.setDescription(sessionData.getDescription());
        session.setEvent(sessionData.getEvent());
        return sessionRepository.save(session);
    }

    public Session deleteSession(long id) {
        Session session = getSessionById(id);
        sessionRepository.delete(session);
        return session;
    }
}
