package com.example.javaspringbootb17.service;

import com.example.javaspringbootb17.entity.User;
import com.example.javaspringbootb17.exception.BadRequestException;
import com.example.javaspringbootb17.exception.ResourceNotFoundException;
import com.example.javaspringbootb17.model.request.LoginRequest;
import com.example.javaspringbootb17.repsitory.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final HttpSession session;

    public void login (LoginRequest request){
        User user = userRepository.findByEmail((request.getEmail()))
                .orElseThrow(()->new ResourceNotFoundException("User not found"));
        if (!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new BadRequestException("Passwrod is incorrect");
        }
        session.setAttribute("currentUser",user);
    }
}
