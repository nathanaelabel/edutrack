package com.metrodata.repositories;

import com.metrodata.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    // Query Method
    Integer countAllByName(String name);

    // Custom Query JPQL
    @Query("SELECT r FROM Room r WHERE r.name = 'Kamar 021'")
    List<Room> findByName(String name);

    @Query("SELECT r FROM Room r WHERE r.name LIKE '%Kamar%'")
    List<Room> findByNameContaining(String name);

    @Query("SELECT r FROM Room r WHERE r.name LIKE %?1%")
    List<Room> findByNameContainingIgnoreCase(String name);

    // Custom Query Native
    @Query(value = "SELECT * FROM tb_m_rooms WHERE name = 'Kamar 021'", nativeQuery = true)
    List<Room> findByNameNative(String name);
}
