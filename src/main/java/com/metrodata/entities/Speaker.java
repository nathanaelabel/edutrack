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
@Table(name = "tb_m_speakers")
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UniqueId;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String photo_url;

    @Column(length = 50, nullable = false)
    private String job_title;

    @Column(length = 100, nullable = false)
    private String company;

    @ManyToOne
    @JoinColumn(name = "sponsor", nullable = false, unique = true)
    private Sponsor sponsor;

    @OneToMany(mappedBy = "speaker")
    private List<SessionSpeaker> sessionSpeakers;
}
