package com.example.javaspringbootb17.repsitory;

import com.example.javaspringbootb17.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}