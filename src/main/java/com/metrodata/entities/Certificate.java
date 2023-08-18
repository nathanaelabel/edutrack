package com.metrodata.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_m_certificate_templates", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String certificate_url;

    @OneToOne
    @JoinColumn(name = "session_detail_id", nullable = false, unique = true)
    private SessionDetail sessionDetail;
}
