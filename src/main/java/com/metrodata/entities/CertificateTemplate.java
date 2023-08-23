package com.metrodata.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_m_certificate_templates")
public class CertificateTemplate {

    @Id
    private Long id;

    @Column(name = "certificate_url", columnDefinition = "TEXT", nullable = false)
    private String certificateUrl;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "session_detail_id", referencedColumnName = "id")
    private SessionDetail sessionDetail;
}
