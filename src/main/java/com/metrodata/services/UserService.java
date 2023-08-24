package com.metrodata.services;

import com.metrodata.entities.models.LoginData;
import com.metrodata.entities.models.LoginResponse;
import com.metrodata.utilities.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtUtil jwtUtil;
    private final RestTemplate restTemplate;
    private final PasswordEncoder passwordEncoder;

    public Map<String, Object> login(LoginData loginData, HttpServletResponse response) {
        LoginResponse loginResponse = restTemplate.postForObject("https://dev.services.metrodataacademy.id/metrodataacademy/user/login", loginData, LoginResponse.class);
        Map<String, Object> result = new LinkedHashMap<>();

        if (loginResponse.getToken() != null && !loginResponse.getToken().isEmpty()) {
            String subject = jwtUtil.extractUsername(loginResponse.getToken());
            String newAccessToken = jwtUtil.generateToken(loginResponse.getToken(), subject);
            String refreshToken = jwtUtil.generateRefreshToken(loginResponse.getToken(), subject);
            setRefreshTokenToCookie(refreshToken, response);

            result.put("token", newAccessToken);
            result.put("exp", new Date(System.currentTimeMillis() + 604800000));
        } else {
            result.put("status", loginResponse.getStatus());
        }
        return result;
    }

    public void logout(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("refreshToken", "")
                .maxAge(0)
                .sameSite("None")
                .secure(true)
                .path("/")
                .httpOnly(true)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    public Map<String, Object> refreshToken(HttpServletResponse response) {
        String oldRefreshToken = jwtUtil.extractClaim(restTemplate.getForObject("https://dev.services.metrodataacademy.id/metrodataacademy/user/refresh-token", String.class), claims -> claims.get("refreshToken", String.class));
        String subject = jwtUtil.extractUsername(oldRefreshToken);
        String newAccessToken = jwtUtil.generateToken(oldRefreshToken, subject);
        String newRefreshToken = jwtUtil.generateRefreshToken(oldRefreshToken, subject);
        setRefreshTokenToCookie(newRefreshToken, response);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("token", newAccessToken);
        result.put("exp", new Date(System.currentTimeMillis() + 604800000));
        return result;
    }

    public void setRefreshTokenToCookie(String token, HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("refreshToken", token)
                .maxAge(604800)
                .sameSite("None")
                .secure(true)
                .path("/")
                .httpOnly(true)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}