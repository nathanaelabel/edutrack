package com.metrodata.entities.models;

import com.metrodata.entities.enums.Occupation;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class ParticipantData {

    private String name, email, university, phoneNumber, address;

    @Enumerated(EnumType.STRING)
    private Occupation occupation;
    private Long eventId;
}
