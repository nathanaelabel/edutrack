package com.metrodata.controllers;

import com.metrodata.entities.Event;
import com.metrodata.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("event")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController (EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("event")
    public List<Event> getEvent() {
        return eventService.getAllEvents();
    }

    @GetMapping("event/{id}")
    public Event getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @PostMapping("event")
    public Event insertEvent(@RequestBody Event event) {
        return eventService.insertEvent(event);
    }

    @PatchMapping("event/{id}")
    public Event updateEvent(@PathVariable long id, @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }

    @DeleteMapping("event/{id}")
    public Event deleteEvent(@PathVariable long id) {
        return eventService.deleteEvent(id);
    }
}
