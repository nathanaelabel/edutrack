package com.metrodata.repositories;

import com.metrodata.entities.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Long> {

    // Query Method
    Integer countAllByName(String name);

    // Custom Query JPQL
    @Query("SELECT s FROM Sponsor s WHERE s.name = 'Metrodata'")
    List<Sponsor> findByName(String name);

    // Custom Query Native
    @Query(value = "SELECT * FROM tb_m_sponsors WHERE name = 'Metrodata'", nativeQuery = true)
    List<Sponsor> findByNameNative(String name);

    @Query(value = "SELECT * FROM tb_m_sponsors WHERE name = ?1", nativeQuery = true)
    List<Sponsor> findByNameNativeId(String name);

    @Query(value = "SELECT * FROM tb_m_sponsors WHERE name = 'M'", nativeQuery = true)
    List<Sponsor> findByNameStartingWith(String name);
}
