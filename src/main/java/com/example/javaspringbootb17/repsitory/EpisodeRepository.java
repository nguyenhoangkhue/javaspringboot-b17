package com.example.javaspringbootb17.repsitory;

import com.example.javaspringbootb17.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
}