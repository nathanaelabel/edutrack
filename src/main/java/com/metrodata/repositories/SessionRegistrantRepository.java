package com.metrodata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRegistrantRepository extends JpaRepository<SessionRegistrantRepository, Long> {

}
