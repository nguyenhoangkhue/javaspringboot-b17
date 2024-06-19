package com.example.javaspringbootb17.service;

import com.example.javaspringbootb17.entity.User;
import com.example.javaspringbootb17.repsitory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    public User getUserDetail(Integer id){
        return userRepository.findUserById(id).orElse(null);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
