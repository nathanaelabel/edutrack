package com.metrodata.services;

import com.metrodata.entities.SessionDetail;
import com.metrodata.entities.models.ResponseData;
import com.metrodata.entities.models.SessionDetailData;
import com.metrodata.repositories.SessionDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SessionDetailService {

    private SessionService sessionService;
    private SessionDetailRepository sessionDetailRepository;

    @Autowired
    public SessionDetailService(SessionDetailRepository sessionDetailRepository) {
        this.sessionDetailRepository = sessionDetailRepository;
    }

    public List<SessionDetail> getAllSessionDetails() {
        return sessionDetailRepository.findAll();
    }

    public SessionDetail getSessionDetailById(Long id) {
        return sessionDetailRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SessionDetail with ID: " + id + " not found"));
    }

    public ResponseData<SessionDetail> insertSessionDetail(SessionDetailData sessionDetailData) {
        try {
            SessionDetail sessionDetail = new SessionDetail();
            sessionDetail.setName(sessionDetailData.getName());
            sessionDetail.setCapacity(sessionDetailData.getCapacity());
            sessionDetail.setDescription(sessionDetailData.getDescription());
            sessionDetail.setSession(sessionService.getSessionById(sessionDetailData.getSessionId()));
            SessionDetail newSessionDetail = sessionDetailRepository.save(sessionDetail);
            return new ResponseData<>(newSessionDetail, "SessionDetail created successfully");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public SessionDetail updateSessionDetail(long id, SessionDetail sessionDetailData) {
        SessionDetail sessionDetail = getSessionDetailById(id);
        sessionDetail.setName(sessionDetailData.getName());
        sessionDetail.setCapacity(sessionDetailData.getCapacity());
        sessionDetail.setDescription(sessionDetailData.getDescription());
        sessionDetail.setSession(sessionService.getSessionById(sessionDetailData.getSession().getId()));
        return sessionDetailRepository.save(sessionDetail);
    }

    public SessionDetail deleteSessionDetail(long id) {
        SessionDetail sessionDetail = getSessionDetailById(id);
        sessionDetailRepository.delete(sessionDetail);
        return sessionDetail;
    }
}
