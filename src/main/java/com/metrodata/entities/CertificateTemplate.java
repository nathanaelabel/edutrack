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
public class CertificateTemplate {

    @Id
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String certificate_url;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "session_detail_id", referencedColumnName = "id")
    private SessionDetail sessionDetail;
}
