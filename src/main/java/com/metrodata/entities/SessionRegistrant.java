package com.metrodata.entities;

import com.metrodata.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_m_session_registrants")
public class SessionRegistrant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UniqueId;

    private LocalDateTime attended_at;

    private Byte is_attended;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private LocalDateTime registered_at;

    @Column(nullable = false)
    private Byte is_reminder_sent;

    @ManyToOne
    @JoinColumn(name = "session_detail_id", nullable = false, unique = true)
    private SessionDetail sessionDetail;

    @ManyToOne
    @JoinColumn(name = "participant_id", nullable = false, unique = true)
    private Participant participant;
}
