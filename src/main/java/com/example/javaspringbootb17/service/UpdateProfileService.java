package com.example.javaspringbootb17.service;

import com.example.javaspringbootb17.entity.User;
import com.example.javaspringbootb17.model.request.UpdateProfileRequest;
import com.example.javaspringbootb17.repsitory.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateProfileService {
    private final UserRepository userRepository;
    private final HttpSession session;
    public void updateProfile(UpdateProfileRequest updateProfileRequest){
        User currentUser = (User) session.getAttribute("currentUser");
        currentUser.setName(updateProfileRequest.getName());
        userRepository.save(currentUser);
    }
}
