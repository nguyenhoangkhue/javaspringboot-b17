package com.example.javaspringbootb17.rest;

import com.example.javaspringbootb17.model.request.CreateUserRequest;
import com.example.javaspringbootb17.model.request.UpdatePasswordRequest;
import com.example.javaspringbootb17.model.request.UpdateProfileRequest;
import com.example.javaspringbootb17.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;

    @PutMapping("/users/update-profile")
    ResponseEntity<?> updateProfile(@RequestBody UpdateProfileRequest request) {
        userService.updateProfile(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/update-password")
    ResponseEntity<?> updatePassword(@Valid @RequestBody UpdatePasswordRequest request) {
        userService.updatePassword(request);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/admin/users")
    ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest request){
        return ResponseEntity.ok(userService.createUser(request));
    }
}
