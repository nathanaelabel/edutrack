package com.metrodata.repositories;

import com.metrodata.entities.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeakerRepository extends JpaRepository<Speaker, Long> {

    // Query Method
    Integer countAllByCompany(String company);

    // Custom Query JPQL
    @Query("SELECT s FROM Speaker s WHERE s.company = 'Metrodata'")
    List<Speaker> findByCompany(String company);

    @Query("SELECT s FROM Speaker s WHERE s.company = ?1")
    List<Speaker> findByIdIn(List<Long> ids);

    // Custom Query Native
    @Query(value = "SELECT * FROM tb_m_speakers WHERE company = 'Metrodata'", nativeQuery = true)
    List<Speaker> findByCompanyNative(String company);
}
