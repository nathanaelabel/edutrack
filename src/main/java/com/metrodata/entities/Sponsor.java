package com.metrodata.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.metrodata.entities.enums.SponsorCategory;
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
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(name = "logo_url", columnDefinition = "TEXT", nullable = false)
    private String logoUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SponsorCategory sponsorCategory;

    @OneToMany(mappedBy = "sponsor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Speaker> speakers;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
}
