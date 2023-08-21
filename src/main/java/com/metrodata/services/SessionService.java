package com.metrodata.services;

import com.metrodata.entities.Session;
import com.metrodata.entities.models.ResponseData;
import com.metrodata.entities.models.SessionData;
import com.metrodata.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SessionService {

    private EventService eventService;
    private SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    public Session getSessionById(Long id) {
        return sessionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Session with ID: " + id + " not found"));
    }

    public ResponseData<Session> insertSession(SessionData sessionData) {
        try {
            Session session = new Session();
            session.setName(sessionData.getName());
            session.setStart_time(sessionData.getStart_time());
            session.setEnd_time(sessionData.getEnd_time());
            session.setDescription(sessionData.getDescription());
            session.setEvent(eventService.getEventById(sessionData.getEventId()));
            Session newSession = sessionRepository.save(session);
            return new ResponseData<>(newSession, "Session created successfully");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
