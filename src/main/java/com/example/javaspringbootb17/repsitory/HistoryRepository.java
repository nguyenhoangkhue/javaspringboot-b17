package com.example.javaspringbootb17.repsitory;

import com.example.javaspringbootb17.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Integer> {
}