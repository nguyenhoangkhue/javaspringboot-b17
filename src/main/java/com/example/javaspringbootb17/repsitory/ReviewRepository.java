package com.example.javaspringbootb17.repsitory;

import com.example.javaspringbootb17.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
