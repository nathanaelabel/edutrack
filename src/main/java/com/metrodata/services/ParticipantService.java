package com.metrodata.services;

import com.metrodata.entities.Participant;
import com.metrodata.entities.models.ParticipantData;
import com.metrodata.entities.models.ResponseData;
import com.metrodata.repositories.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final EventService eventService;

    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    public Participant getParticipantById(Long id) {
        return participantRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Participant with ID: " + id + " not found"));
    }

    public ResponseData<Participant> insertParticipant(ParticipantData participantData) {
        try {
            Participant participant = new Participant();
            participant.setName(participantData.getName());
            participant.setEmail(participantData.getEmail());
            participant.setUniversity(participantData.getUniversity());
            participant.setPhoneNumber(participantData.getPhoneNumber());
            participant.setAddress(participantData.getAddress());
            participant.setOccupation(participantData.getOccupation());
            participant.setEvent(eventService.getEventById(participantData.getEventId()));
            Participant newParticipant = participantRepository.save(participant);
            return new ResponseData<>(newParticipant, "Participant created successfully");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public Participant updateParticipant(long id, Participant participantData) {
        Participant participant = getParticipantById(id);
        participant.setName(participantData.getName());
        participant.setEmail(participantData.getEmail());
        participant.setUniversity(participantData.getUniversity());
        participant.setPhoneNumber(participantData.getPhoneNumber());
        participant.setAddress(participantData.getAddress());
        participant.setOccupation(participantData.getOccupation());
        participant.setEvent(eventService.getEventById(participantData.getEvent().getId()));
        return participantRepository.save(participant);
    }

    public Participant deleteParticipant(long id) {
        Participant participant = getParticipantById(id);
        participantRepository.delete(participant);
        return participant;
    }
}
