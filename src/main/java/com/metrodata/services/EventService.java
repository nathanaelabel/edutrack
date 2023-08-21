package com.metrodata.services;

import com.metrodata.entities.Event;
import com.metrodata.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EventService {

    private EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Event with id %d not found", id)));
    }

    public Event insertEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(long id, Event eventData) {
        Event event = getEventById(id);
        event.setName(eventData.getName());
        event.setStart_date(eventData.getStart_date());
        event.setEnd_date(eventData.getEnd_date());
        event.setStart_registration(eventData.getStart_registration());
        event.setEnd_registration(eventData.getEnd_registration());
        event.setCapacity(eventData.getCapacity());
        event.setDescription(eventData.getDescription());
        event.setLocation(eventData.getLocation());
        event.setImage_url(eventData.getImage_url());
        event.setStatus(eventData.getStatus());
        return eventRepository.save(event);
    }

    public Event deleteEvent(long id) {
        Event event = getEventById(id);
        eventRepository.delete(event);
        return event;
    }
}
