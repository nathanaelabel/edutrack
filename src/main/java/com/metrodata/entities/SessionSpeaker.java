package com.metrodata.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_tr_session_speakers")
public class SessionSpeaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "speaker_id", nullable = false)
    private Speaker speaker;

    @ManyToOne
    @JoinColumn(name = "session_detail_id", nullable = false)
    private SessionDetail sessionDetail;
}
