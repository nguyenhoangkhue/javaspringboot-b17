package com.example.javaspringbootb17.rest;

import com.example.javaspringbootb17.model.request.LoginRequest;
import com.example.javaspringbootb17.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
public class AuthApi {
    private final AuthService authService;
    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        authService.login(request);
        return ResponseEntity.ok().build();
    }
}
