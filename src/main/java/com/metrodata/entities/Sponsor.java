package com.metrodata.entities;

import com.metrodata.enums.SponsorCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_m_sponsors")
public class Sponsor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UniqueId;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String logo_url;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SponsorCategory sponsorCategory;

    @OneToMany(mappedBy = "sponsor")
    private List<Speaker> speakers;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false, unique = true)
    private Event event;
}
