package com.metrodata.repositories;

import com.metrodata.entities.Participant;
import com.metrodata.entities.enums.Occupation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantRepository, Long> {

    // Query Method
    Integer countAllByEmail(String email);

    List<Participant> findByOccupation(Occupation occupation);

    // Custom Query JPQL
    @Query("SELECT p FROM Participant p WHERE p.occupation = 'Mahasiswa'")
    List<Participant> findByOccupation(String occupation);

    // Custom Query Native
    @Query(value = "SELECT * FROM tb_m_participants WHERE occupation = 'Dosen'", nativeQuery = true)
    List<Participant> findByOccupationNative(String occupation);

}
