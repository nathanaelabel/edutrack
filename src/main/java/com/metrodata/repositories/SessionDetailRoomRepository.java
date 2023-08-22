package com.metrodata.repositories;

import com.metrodata.entities.SessionDetailRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionDetailRoomRepository extends JpaRepository<SessionDetailRoom, Long> {

    // Query Method
    Integer countById(Long id);

    // Custom Query JPQL
    @Query("SELECT sdr FROM SessionDetailRoom sdr WHERE sdr.sessionDetail.capacity < :capacity")
    List<SessionDetailRoom> findBySessionDetailCapacityLessThan(Integer capacity);

    // Custom Query Native
    @Query(value = "SELECT * FROM session_detail_room sdr WHERE sdr.session_detail_id = :sessionId", nativeQuery = true)
    List<SessionDetailRoom> findBySessionDetailId(Long sessionId);
}
