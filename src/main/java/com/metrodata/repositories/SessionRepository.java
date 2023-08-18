package com.metrodata.repositories;

import com.metrodata.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<SessionRepository, Long> {

    // Query Method
    Integer countAllByName(String name);

    // Custom Query JPQL
    @Query("SELECT s FROM Session s WHERE s.name = ?1")
    List<Session> findByName(String name);

    // Custom Query Native
    @Query(value = "SELECT * FROM tb_m_sessions WHERE name = ?1", nativeQuery = true)
    List<Session> findByNameNative(String name);
}
