package com.metrodata.repositories;

import com.metrodata.entities.CertificateTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateTemplateRepository extends JpaRepository<CertificateTemplate, Long>{

    // Query Method
    Integer countAllByCertificate_url(String certificate_url);

    // Custom Query JPQL
    @Query("SELECT c FROM CertificateTemplate c WHERE c.certificate_url = ?1")
    List<CertificateTemplate> findByCertificate_url(String certificate_url);

    // Custom Query Native
    @Query(value = "SELECT * FROM tb_m_certificate_templates WHERE certificate_url = ?1", nativeQuery = true)
    List<CertificateTemplate> findByCertificate_urlNative(String certificate_url);
}
