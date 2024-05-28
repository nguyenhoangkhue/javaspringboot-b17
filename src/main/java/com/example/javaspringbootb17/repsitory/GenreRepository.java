package com.example.javaspringbootb17.repsitory;

import com.example.javaspringbootb17.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
