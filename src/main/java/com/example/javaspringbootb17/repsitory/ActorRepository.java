package com.example.javaspringbootb17.repsitory;

import com.example.javaspringbootb17.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
}