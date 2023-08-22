package com.metrodata.entities.models;

import lombok.Data;

@Data
public class SessionDetailData {

    private String name;
    private Integer capacity;
    private String description;
    private Long sessionId;
}
