package com.example.javaspringbootb17.repsitory;

import com.example.javaspringbootb17.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
}
