package com.metrodata.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    @Column(name = "start_registration", nullable = false)
    private LocalDateTime startRegistration;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    @Column(name = "end_registration", nullable = false)
    private LocalDateTime endRegistration;

    @Column(nullable = false)
    private Integer capacity;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String location;

    @Column(name = "image_url", columnDefinition = "TEXT", nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private Byte status;

    @Column(name = "is_published", nullable = false)
    private Boolean isPublished;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Sponsor> sponsors;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Participant> participants;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Session> sessions;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Room> rooms;

//    @ManyToMany
//    @JoinTable(
//            name = "tb_m_participant_events",
//            joinColumns = @JoinColumn(name = "event_id"),
//            inverseJoinColumns = @JoinColumn(name = "participant_id")
//    )
//    Set<Event> participantEvents;
}
