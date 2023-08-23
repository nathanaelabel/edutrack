package com.metrodata.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.metrodata.entities.enums.Occupation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_m_participants")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String university;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Enumerated(EnumType.STRING)
    private Occupation occupation;

    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<SessionRegistrant> sessionRegistrants;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

//    @ManyToMany
//    @JoinTable(
//            name = "tb_m_participant_events",
//            joinColumns = @JoinColumn(name = "participant_id"),
//            inverseJoinColumns = @JoinColumn(name = "event_id")
//    )
//    Set<Participant> participantEvents;

}
