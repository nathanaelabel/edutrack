package com.metrodata.controllers;

import com.metrodata.entities.Sponsor;
import com.metrodata.entities.models.ResponseData;
import com.metrodata.entities.models.SponsorData;
import com.metrodata.services.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sponsor")
public class SponsorController {

    private SponsorService sponsorService;

    @Autowired
    public SponsorController(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }

    @GetMapping
    public List<Sponsor> getSponsor() {
        return sponsorService.getAllSponsors();
    }

    @GetMapping("{id}")
    public Sponsor getSponsorById(@PathVariable Long id) {
        return sponsorService.getSponsorById(id);
    }

    @PostMapping
    public ResponseData<Sponsor> insertSponsor(@RequestBody SponsorData sponsorData) {
        return sponsorService.insertSponsor(sponsorData);
    }

    @PatchMapping("{id}")
    public Sponsor updateSponsor(@PathVariable long id, @RequestBody Sponsor sponsor) {
        return sponsorService.updateSponsor(id, sponsor);
    }

    @DeleteMapping("{id}")
    public Sponsor deleteSponsor(@PathVariable long id) {
        return sponsorService.deleteSponsor(id);
    }
}
