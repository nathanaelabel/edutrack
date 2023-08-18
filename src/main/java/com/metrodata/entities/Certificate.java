package com.metrodata.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_m_certificates", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String image_url;

    @OneToOne
    @JoinColumn(name = "sponsor_id", nullable = false)
    private Sponsor sponsor;
}
