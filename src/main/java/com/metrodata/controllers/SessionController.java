package com.metrodata.controllers;

import com.metrodata.entities.Session;
import com.metrodata.entities.models.ResponseData;
import com.metrodata.entities.models.SessionData;
import com.metrodata.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("session")
public class SessionController {

    private SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public List<Session> getSession() {
        return sessionService.getAllSessions();
    }

    @GetMapping("{id}")
    public Session getSessionById(@PathVariable Long id) {
        return sessionService.getSessionById(id);
    }

    @PostMapping
    public ResponseData<Session> insertSession(@RequestBody SessionData sessionData) {
        return sessionService.insertSession(sessionData);
    }

    @PatchMapping("{id}")
    public Session updateSession(@PathVariable long id, @RequestBody Session session) {
        return sessionService.updateSession(id, session);
    }

    @DeleteMapping("{id}")
    public Session deleteSession(@PathVariable long id) {
        return sessionService.deleteSession(id);
    }
}
