package com.example.javaspringbootb17.repsitory;

import com.example.javaspringbootb17.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
    List<Episode>findByMovie_IdAndStatusOrderByDisplayOrderAsc(Integer movieId,Boolean status);
    Optional <Episode> findByMovie_IdAndStatusAndDisplayOrder(Integer movieId, Boolean status, Integer order);
    List<Episode>findByMovie_IdOrderByDisplayOrderAsc(Integer movieId);
}