package com.metrodata.controllers;

import com.metrodata.entities.models.LoginData;
import com.metrodata.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginData loginData, HttpServletResponse response) {
        return userService.login(loginData, response);
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response) {
        userService.logout(response);
    }

    @PostMapping("/refresh-token")
    public Map<String, Object> refreshToken(HttpServletResponse response) {
        return userService.refreshToken(response);
    }
}
