package com.example.javaspringbootb17.rest;

import com.example.javaspringbootb17.model.request.LoginRequest;
import com.example.javaspringbootb17.model.request.UpdatePasswordRequest;
import com.example.javaspringbootb17.model.request.UpdateProfileRequest;
import com.example.javaspringbootb17.service.AuthService;
import com.example.javaspringbootb17.service.UpdateProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UpdateProfileApi {
    private final UpdateProfileService updateProfileService;
    @PostMapping("/api/users/update-profile")
    public ResponseEntity<?> updatePassword(@RequestBody UpdateProfileRequest updateProfileRequest){
        updateProfileService.updateProfile(updateProfileRequest);
        return ResponseEntity.ok().build();
    }
}
