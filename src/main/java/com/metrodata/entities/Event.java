package com.metrodata.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_m_events")
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

//    @Column(name = "start_date_time", nullable = false)
//    private LocalDateTime startDateTime;

//    @Column(name = "end_date_time", nullable = false)
//    private LocalDateTime endDateTime;

    @Column(nullable = false)
    private Integer capacity;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String location;

    @Column(nullable = false)
    private Byte status;

    @OneToMany(mappedBy = "event")
    private List<Sponsor> sponsors;

    @ManyToMany
    @JoinTable(
            name = "participant_events",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id")
    )
    Set<Event> participantEvents;
}
