package com.metrodata.controllers;

import com.metrodata.entities.Speaker;
import com.metrodata.entities.models.ResponseData;
import com.metrodata.entities.models.SpeakerData;
import com.metrodata.services.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("speaker")
public class SpeakerController {

    private SpeakerService speakerService;

    @Autowired
    public SpeakerController(SpeakerService speakerService) {
        this.speakerService = speakerService;
    }

    @GetMapping
    public List<Speaker> getSpeaker() {
        return speakerService.getAllSpeakers();
    }

    @GetMapping("{id}")
    public Speaker getSpeakerById(Long id) {
        return speakerService.getSpeakerById(id);
    }

    @PostMapping
    public ResponseData<Speaker> insertSpeaker(SpeakerData speakerData) {
        return speakerService.insertSpeaker(speakerData);
    }

    @PostMapping("{id}")
    public Speaker updateSpeaker(long id, Speaker speaker) {
        return speakerService.updateSpeaker(id, speaker);
    }

    @DeleteMapping("{id}")
    public Speaker deleteSpeaker(long id) {
        return speakerService.deleteSpeaker(id);
    }
}
