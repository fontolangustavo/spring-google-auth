package com.fontolan.spring.google.auth.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String jwtToken;
}