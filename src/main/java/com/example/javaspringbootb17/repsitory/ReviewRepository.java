package com.example.javaspringbootb17.repsitory;

import com.example.javaspringbootb17.entity.Movie;
import com.example.javaspringbootb17.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findAllByMovie_Id(Integer movieId);
}
