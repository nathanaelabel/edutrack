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
@Table(name = "tb_m_speakers", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(name = "photo_url", columnDefinition = "TEXT", nullable = false)
    private String photoUrl;

    @Column(name = "job_title", length = 50, nullable = false)
    private String jobTitle;

    @Column(length = 100, nullable = false)
    private String company;

    @ManyToOne
    @JoinColumn(name = "sponsor", nullable = false, unique = true)
    private Sponsor sponsor;

    @OneToMany(mappedBy = "speaker", cascade = CascadeType.ALL)
    private List<SessionSpeaker> sessionSpeakers;
}
