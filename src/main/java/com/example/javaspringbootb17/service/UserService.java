package com.example.javaspringbootb17.service;


import com.example.javaspringbootb17.entity.User;
import com.example.javaspringbootb17.exception.BadRequestException;
import com.example.javaspringbootb17.exception.ResourceNotFoundException;
import com.example.javaspringbootb17.model.request.*;
import com.example.javaspringbootb17.repsitory.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final HttpSession session;
    private final BCryptPasswordEncoder passwordEncoder;

    public void updateProfile(UpdateProfileRequest request) {
        User user = (User) session.getAttribute("currentUser");
        user.setName(request.getName());

        session.setAttribute("currentUser", user);
        userRepository.save(user);
    }

    public void updatePassword(UpdatePasswordRequest request) {
        User user = (User) session.getAttribute("currentUser");

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new BadRequestException("Mật khẩu cũ không đúng");
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException("Mật khẩu xác nhận không khớp");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        session.setAttribute("currentUser", user);
        userRepository.save(user);
    }
    public User createUser(CreateUserRequest request){
        Optional<User>userOptional=userRepository.findByEmail(request.getEmail());
        if (userOptional.isPresent()){
            throw new BadRequestException("Email đã tồn tại");
        }
        User user=User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .avatar("https://placehold.co/600x400?text=" + request.getName())
                .role(request.getRole())
                .password(passwordEncoder.encode("123"))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return userRepository.save(user);
    }
    public void adminUpdateProfile(AdminUpdateProfileRequest request, Integer id) {
        User user = userRepository.findUserById(id).orElseThrow(()->new ResourceNotFoundException("User not found"));
        user.setName(request.getName());

        userRepository.save(user);
    }
    public void resetPassword(Integer id) {
        User user = userRepository.findUserById(id).orElseThrow(()->new ResourceNotFoundException("User not found"));
        user.setPassword(passwordEncoder.encode("123"));

        userRepository.save(user);
    }
}