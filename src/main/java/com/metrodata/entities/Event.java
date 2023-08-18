package com.metrodata.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_m_events", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String slug;

    @Column(nullable = false)
    private LocalTime start_time;

    @Column(nullable = false)
    private LocalDate start_date;

    @Column(nullable = false)
    private LocalDate end_date;

    @Column(nullable = false)
    private LocalDateTime start_registration;

    @Column(nullable = false)
    private LocalDateTime end_registration;

    @Column(nullable = false)
    private Integer capacity;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String location;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String image_url;

    @Column(nullable = false)
    private Byte status;

    @OneToMany(mappedBy = "event")
    private List<Sponsor> sponsors;

    @OneToMany(mappedBy = "event")
    private List<Participant> participants;

    @OneToMany(mappedBy = "event")
    private List<Session> sessions;

    @OneToMany(mappedBy = "event")
    private List<Room> rooms;

//    @ManyToMany
//    @JoinTable(
//            name = "tb_m_participant_events",
//            joinColumns = @JoinColumn(name = "event_id"),
//            inverseJoinColumns = @JoinColumn(name = "participant_id")
//    )
//    Set<Event> participantEvents;
}
