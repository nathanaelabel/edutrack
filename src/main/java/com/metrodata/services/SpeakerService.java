package com.metrodata.services;

import com.metrodata.entities.Speaker;
import com.metrodata.entities.models.ResponseData;
import com.metrodata.entities.models.SpeakerData;
import com.metrodata.repositories.SpeakerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SpeakerService {

    private SpeakerRepository speakerRepository;
    private SponsorService sponsorService;

    public SpeakerService(SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;
    }

    public List<Speaker> getAllSpeakers() {
        return speakerRepository.findAll();
    }

    public Speaker getSpeakerById(Long id) {
        return speakerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Speaker with ID: " + id + " not found"));
    }

    public ResponseData<Speaker> insertSpeaker(SpeakerData speakerData) {
        try {
            Speaker speaker = new Speaker();
            speaker.setName(speakerData.getName());
            speaker.setPhotoUrl(speakerData.getPhotoUrl());
            speaker.setJobTitle(speakerData.getJobTitle());
            speaker.setCompany(speakerData.getCompany());
            speaker.setSponsor(sponsorService.getSponsorById(speakerData.getSponsorId()));
            Speaker newSpeaker = speakerRepository.save(speaker);
            return new ResponseData<>(newSpeaker, "Speaker created successfully");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public Speaker updateSpeaker(long id, Speaker speakerData) {
        Speaker speaker = getSpeakerById(id);
        speaker.setName(speakerData.getName());
        speaker.setPhotoUrl(speakerData.getPhotoUrl());
        speaker.setJobTitle(speakerData.getJobTitle());
        speaker.setCompany(speakerData.getCompany());
        speaker.setSponsor(sponsorService.getSponsorById(speakerData.getSponsor().getId()));
        return speakerRepository.save(speaker);
    }

    public Speaker deleteSpeaker(long id) {
        Speaker speaker = getSpeakerById(id);
        speakerRepository.delete(speaker);
        return speaker;
    }
}
