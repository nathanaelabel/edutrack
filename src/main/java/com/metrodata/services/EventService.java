package com.metrodata.services;

import com.metrodata.entities.Event;
import com.metrodata.entities.models.EventData;
import com.metrodata.entities.models.ResponseData;
import com.metrodata.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event with ID: " + id + " not found"));
    }

    public ResponseData<Event> insertEvent(EventData eventData) {
        try {
            Event event = new Event();
            event.setName(eventData.getName());
            event.setSlug(eventData.getSlug());
            event.setStartTime(eventData.getStartTime());
            event.setStartDate(eventData.getStartDate());
            event.setEndDate(eventData.getEndDate());
            event.setStartRegistration(eventData.getStartRegistration());
            event.setEndRegistration(eventData.getEndRegistration());
            event.setCapacity(eventData.getCapacity());
            event.setDescription(eventData.getDescription());
            event.setLocation(eventData.getLocation());
            event.setImageUrl(eventData.getImageUrl());
            event.setStatus(eventData.getStatus());
            event.setIsPublished(eventData.getIsPublished());
            Event newEvent = eventRepository.save(event);
            return new ResponseData<>(newEvent, "Event created successfully");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public Event updateEvent(long id, Event eventData) {
        Event event = getEventById(id);
        event.setName(eventData.getName());
        event.setSlug(eventData.getSlug());
        event.setStartTime(eventData.getStartTime());
        event.setStartDate(eventData.getStartDate());
        event.setEndDate(eventData.getEndDate());
        event.setStartRegistration(eventData.getStartRegistration());
        event.setEndRegistration(eventData.getEndRegistration());
        event.setCapacity(eventData.getCapacity());
        event.setDescription(eventData.getDescription());
        event.setLocation(eventData.getLocation());
        event.setImageUrl(eventData.getImageUrl());
        event.setStatus(eventData.getStatus());
        event.setIsPublished(eventData.getIsPublished());
        return eventRepository.save(event);
    }

    public Event deleteEvent(long id) {
        Event event = getEventById(id);
        eventRepository.delete(event);
        return event;
    }
}
