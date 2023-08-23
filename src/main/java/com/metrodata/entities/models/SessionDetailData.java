package com.metrodata.entities.models;

import lombok.Data;

@Data
public class SessionDetailData {

    private String name, description;
    private Integer capacity;
    private Long sessionId;
}
