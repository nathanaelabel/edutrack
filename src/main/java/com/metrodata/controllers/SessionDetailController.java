package com.metrodata.controllers;

import com.metrodata.entities.SessionDetail;
import com.metrodata.entities.models.ResponseData;
import com.metrodata.entities.models.SessionDetailData;
import com.metrodata.services.SessionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("session-detail")
public class SessionDetailController {

    private SessionDetailService sessionDetailService;

    @Autowired
    public SessionDetailController(SessionDetailService sessionDetailService) {
        this.sessionDetailService = sessionDetailService;
    }

    @GetMapping
    public List<SessionDetail> getSessionDetail() {
        return sessionDetailService.getAllSessionDetails();
    }

    @GetMapping("{id}")
    public SessionDetail getSessionDetailById(@PathVariable Long id) {
        return sessionDetailService.getSessionDetailById(id);
    }

    @PostMapping
    public ResponseData<SessionDetail> insertSessionDetail(@RequestBody SessionDetailData sessionDetailData) {
        return sessionDetailService.insertSessionDetail(sessionDetailData);
    }

    @PatchMapping("{id}")
    public SessionDetail updateSessionDetail(@PathVariable long id, @RequestBody SessionDetail sessionDetail) {
        return sessionDetailService.updateSessionDetail(id, sessionDetail);
    }

    @DeleteMapping("{id}")
    public SessionDetail deleteSessionDetail(long id) {
        return sessionDetailService.deleteSessionDetail(id);
    }
}
