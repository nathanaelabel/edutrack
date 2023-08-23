package com.metrodata.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.metrodata.entities.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_tr_session_registrants")
public class SessionRegistrant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    @Column(name = "attended_at", nullable = false)
    private LocalDateTime attendedAt;

    @Column(name = "is_attended", nullable = false)
    private Boolean isAttended;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(name = "registered_at", nullable = false)
    private LocalDateTime registeredAt;

    @Column(name = "is_reminder_sent", nullable = false)
    private Byte isReminderSent;

    @ManyToOne
    @JoinColumn(name = "session_detail_id", nullable = false)
    private SessionDetail sessionDetail;

    @ManyToOne
    @JoinColumn(name = "participant_id", nullable = false)
    private Participant participant;
}
