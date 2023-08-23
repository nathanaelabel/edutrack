package com.metrodata.entities.models;

import lombok.Data;

@Data
public class EmailData {
    private String to, subject, body;
}
