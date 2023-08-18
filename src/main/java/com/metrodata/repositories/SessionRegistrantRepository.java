package com.metrodata.repositories;

import com.metrodata.entities.SessionRegistrant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRegistrantRepository extends JpaRepository<SessionRegistrant, Long> {

    // Query Method
    Integer countAllByStatus(String status);

    // Custom Query JPQL
    @Query("SELECT p FROM SessionRegistrant p WHERE p.status = 'Active'")
    List<SessionRegistrant> findByStatus(String status);

    // Custom Query Native
    @Query(value = "SELECT * FROM tb_m_session_registrants WHERE status = 'Active' AND is_reminder_sent = '1'", nativeQuery = true)
    List<SessionRegistrant> findByStatusNative(String status);
}
