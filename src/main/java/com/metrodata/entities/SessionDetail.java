package com.metrodata.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_m_session_details", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class SessionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer capacity;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToOne(mappedBy = "sessionDetail")
    private Certificate certificate;

    @OneToMany(mappedBy = "sessionDetail")
    private List<SessionSpeaker> sessionSpeakers;

    @OneToMany(mappedBy = "sessionDetail")
    private List<SessionDetailRoom> sessionDetailRooms;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false, unique = true)
    private Session session;
}
