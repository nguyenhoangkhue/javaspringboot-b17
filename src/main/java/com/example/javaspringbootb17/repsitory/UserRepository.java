package com.example.javaspringbootb17.repsitory;

import com.example.javaspringbootb17.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
