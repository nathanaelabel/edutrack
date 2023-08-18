package com.metrodata.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_tr_session_detail_rooms", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class SessionDetailRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_detail_id", nullable = false, unique = true)
    private SessionDetail sessionDetail;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false, unique = true)
    private Room room;
}
