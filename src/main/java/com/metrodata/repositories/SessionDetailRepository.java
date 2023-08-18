package com.metrodata.repositories;

import com.metrodata.entities.SessionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionDetailRepository extends JpaRepository<SessionDetailRepository, Long> {

    // Query Method
    Integer countAllByCapacity(Integer capacity);

    // Custom Query JPQL
    @Query("SELECT sd FROM SessionDetail sd WHERE sd.capacity = ?2")
    List<SessionDetail> findByCapacity(Integer capacity);

    // Custom Query Native
    @Query(value = "SELECT * FROM tb_m_session_details WHERE name = ?1 AND capacity = ?2", nativeQuery = true)
    List<SessionDetail> findByCapacityNative(Integer capacity);
}
