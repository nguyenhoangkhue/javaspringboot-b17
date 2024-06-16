package com.example.javaspringbootb17.rest;

import com.example.javaspringbootb17.model.request.UpdatePasswordRequest;
import com.example.javaspringbootb17.service.UpdatePasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UpdatePasswordApi {
    private final UpdatePasswordService updatePasswordService;
    @PostMapping("/api/users/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest){
        updatePasswordService.updatePassword(updatePasswordRequest);
        return ResponseEntity.ok().build();
    }
}
