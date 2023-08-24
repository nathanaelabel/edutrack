package com.metrodata.entities.models;

import lombok.Data;

@Data
public class LoginResponse {

    private String exp, token, status;
}
