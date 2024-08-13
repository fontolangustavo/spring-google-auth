package com.fontolan.spring.google.auth.controllers;

import com.fontolan.spring.google.auth.controllers.request.TokenRequest;
import com.fontolan.spring.google.auth.controllers.response.AuthResponse;
import com.fontolan.spring.google.auth.domains.User;
import com.fontolan.spring.google.auth.repositories.UserRepository;
import com.fontolan.spring.google.auth.services.GoogleTokenService;
import com.fontolan.spring.google.auth.services.JwtTokenService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final GoogleTokenService googleTokenService;
    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;

    public AuthController(GoogleTokenService googleTokenService, JwtTokenService jwtTokenService, UserRepository userRepository) {
        this.googleTokenService = googleTokenService;
        this.jwtTokenService = jwtTokenService;
        this.userRepository = userRepository;
    }

    @PostMapping("/google")
    public ResponseEntity<AuthResponse> googleLogin(@RequestBody TokenRequest tokenRequest) throws Exception {
        GoogleIdToken idToken = googleTokenService.verifyToken(tokenRequest.getToken());
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            String googleId = payload.getSubject();
            String email = payload.getEmail();
            String name = (String) payload.get("name");

            User user = userRepository.findByEmail(email);
            if (user == null) {
                user = User.builder()
                    .googleId(googleId)
                    .email(email)
                    .name(name)
                    .build();

                userRepository.save(user);
            }

            String jwtToken = jwtTokenService.generateJwtToken(user);

            return ResponseEntity.ok(new AuthResponse(jwtToken));
        } else {
            throw new RuntimeException("Invalid Google token");
        }
    }
}
