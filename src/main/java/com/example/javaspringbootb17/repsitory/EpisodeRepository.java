package com.example.javaspringbootb17.repsitory;

import com.example.javaspringbootb17.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
    List<Episode>findByMovie_IdAndStatusOrderByDisplayOrderDesc(Integer movieId,Boolean status);
}