package com.metrodata.repositories;

import com.metrodata.entities.SessionSpeaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionSpeakerRepository extends JpaRepository<SessionSpeaker, Long> {

    // Query Method
    Integer countAllById(Long id);

    // Custom Query JPQL
    @Query("SELECT ss FROM SessionSpeaker ss WHERE ss.id = ?1")
    List<SessionSpeaker> findBySessionId(Long sessionId);

    // Custom Query Native
    @Query(value = "SELECT * FROM tb_m_session_speakers WHERE id = ?1", nativeQuery = true)
    List<SessionSpeaker> findBySessionIdNative(Long sessionId);
}
