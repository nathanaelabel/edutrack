package com.metrodata.services;

import com.metrodata.entities.CertificateTemplate;
import com.metrodata.entities.models.ResponseData;
import com.metrodata.repositories.CertificateTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CertificateTemplateService {

    private SessionDetailService sessionDetailService;
    private CertificateTemplateRepository certificateTemplateRepository;

    @Autowired
    public CertificateTemplateService(CertificateTemplateRepository certificateTemplateRepository) {
        this.certificateTemplateRepository = certificateTemplateRepository;
    }

    public List<CertificateTemplate> getAllCertificateTemplates() {
        return certificateTemplateRepository.findAll();
    }

    public CertificateTemplate getCertificateTemplateById(Long id) {
        return certificateTemplateRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CertificateTemplate with ID: " + id + " not found"));
    }

    public ResponseData<CertificateTemplate> insertCertificateTemplate(CertificateTemplate certificateTemplate) {
        try {
            CertificateTemplate newCertificateTemplate = certificateTemplateRepository.save(certificateTemplate);
            return new ResponseData<>(newCertificateTemplate, "Certificate created successfully");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public CertificateTemplate updateCertificateTemplate(long id, CertificateTemplate certificateTemplateData) {
        CertificateTemplate certificateTemplate = getCertificateTemplateById(id);
        certificateTemplate.setCertificate_url(certificateTemplateData.getCertificate_url());

        // Tidak ada FK dari sessionDetail di CertificateTemplate karena sudah ada OneToOne dan bergabung dalam Primary Key di SessionDetail
        certificateTemplate.setSessionDetail(sessionDetailService.getSessionDetailById(certificateTemplateData.getSessionDetail().getId()));

        return certificateTemplateRepository.save(certificateTemplate);
    }
}
