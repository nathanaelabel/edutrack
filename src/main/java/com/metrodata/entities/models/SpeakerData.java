package com.metrodata.entities.models;

import lombok.Data;

@Data
public class SpeakerData {

    private String name, photoUrl, jobTitle, company;
    private Long sponsorId;
}
