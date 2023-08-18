package com.metrodata.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_m_session_details")
public class SessionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UniqueId;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer capacity;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false, unique = true)
    private Session session;
}
