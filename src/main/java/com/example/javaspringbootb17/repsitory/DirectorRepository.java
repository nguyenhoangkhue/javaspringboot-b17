package com.example.javaspringbootb17.repsitory;

import com.example.javaspringbootb17.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Integer> {
}
