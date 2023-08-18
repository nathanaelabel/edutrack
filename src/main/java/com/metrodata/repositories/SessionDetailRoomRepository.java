package com.metrodata.repositories;

import com.metrodata.entities.SessionDetailRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionDetailRoomRepository extends JpaRepository<SessionDetailRoom, Long> {

    // Query Method
    List<SessionDetailRoom> findBySessionDetailRoomId(String sessionDetailRoomId);

    // Custom Query JPQL
    @Query("SELECT s FROM SessionDetailRoom s WHERE s.id = ?1 AND s.room = ?2")
    List<SessionDetailRoom> findBySessionIdAndRoomId(String sessionDetailRoomId, String roomId);

    // Custom Query Native
    @Query(value = "SELECT * FROM tb_tr_session_detail_rooms WHERE session_detail_id = ?1 AND room_id = ?2", nativeQuery = true)
    List<SessionDetailRoom> findBySessionIdAndRoomIdNative(String sessionDetailRoomId, String roomId);
}
