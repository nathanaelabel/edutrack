package com.metrodata.repositories;

import com.metrodata.entities.CertificateTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateTemplateRepository extends JpaRepository<CertificateTemplate, Long> {

    // Query Method
    Integer countByCertificateUrl(String certificateUrl);

    // Custom Query JPQL
    @Query("SELECT c FROM CertificateTemplate c WHERE c.certificateUrl = ?1")
    List<CertificateTemplate> findByCertificateUrl(String certificateUrl);

    // Custom Query Native
    @Query(value = "SELECT * FROM certificate_template WHERE certificateUrl = ?1", nativeQuery = true)
    List<CertificateTemplate> findByCertificateUrlNative(String certificateUrl);
}
