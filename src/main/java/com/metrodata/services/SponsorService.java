package com.metrodata.services;

import com.metrodata.entities.Sponsor;
import com.metrodata.entities.models.ResponseData;
import com.metrodata.entities.models.SponsorData;
import com.metrodata.repositories.SponsorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SponsorService {

    private final SponsorRepository sponsorRepository;
    private final EventService eventService;

    public List<Sponsor> getAllSponsors() {
        return sponsorRepository.findAll();
    }

    public Sponsor getSponsorById(Long id) {
        return sponsorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sponsor with ID: " + id + " not found"));
    }

    public ResponseData<Sponsor> insertSponsor(SponsorData sponsorData) {
        try {
            Sponsor sponsor = new Sponsor();
            sponsor.setName(sponsorData.getName());
            sponsor.setLogoUrl(sponsorData.getLogoUrl());
            sponsor.setSponsorCategory(sponsorData.getSponsorCategory());
            sponsor.setEvent(eventService.getEventById(sponsorData.getEventId()));
            Sponsor newSponsor = sponsorRepository.save(sponsor);
            return new ResponseData<>(newSponsor, "Sponsor created successfully");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public Sponsor updateSponsor(long id, Sponsor sponsorData) {
        Sponsor sponsor = getSponsorById(id);
        sponsor.setName(sponsorData.getName());
        sponsor.setLogoUrl(sponsorData.getLogoUrl());
        sponsor.setSponsorCategory(sponsorData.getSponsorCategory());
        sponsor.setEvent(eventService.getEventById(sponsorData.getEvent().getId()));
        return sponsorRepository.save(sponsor);
    }

    public Sponsor deleteSponsor(long id) {
        Sponsor sponsor = getSponsorById(id);
        sponsorRepository.delete(sponsor);
        return sponsor;
    }
}
