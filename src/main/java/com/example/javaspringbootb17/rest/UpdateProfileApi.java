package com.example.javaspringbootb17.rest;

import com.example.javaspringbootb17.model.request.UpdateProfileRequest;
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
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileRequest updateProfileRequest){
        updateProfileService.updateProfile(updateProfileRequest);
        return ResponseEntity.ok().build();
    }
}
