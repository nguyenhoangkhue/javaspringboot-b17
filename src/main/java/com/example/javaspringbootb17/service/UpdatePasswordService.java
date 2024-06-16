package com.example.javaspringbootb17.service;

import com.example.javaspringbootb17.entity.User;
import com.example.javaspringbootb17.model.request.UpdatePasswordRequest;
import com.example.javaspringbootb17.repsitory.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePasswordService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final HttpSession session;
    public void updatePassword(UpdatePasswordRequest updatePasswordRequest){
        User currentUser = (User) session.getAttribute("currentUser");

        if (!bCryptPasswordEncoder.matches(updatePasswordRequest.getOldPassword(), currentUser.getPassword())) {
            throw new RuntimeException();
        }

        if (!updatePasswordRequest.getNewPassword().equals(updatePasswordRequest.getConfirmPassword())) {
            throw new RuntimeException();
        }

        currentUser.setPassword(bCryptPasswordEncoder.encode(updatePasswordRequest.getNewPassword()));
        userRepository.save(currentUser);
    }
}
