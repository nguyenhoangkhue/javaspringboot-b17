package com.example.javaspringbootb17.repsitory;

import com.example.javaspringbootb17.entity.Movie;
import com.example.javaspringbootb17.model.enums.MovieType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {
    //Normal query methods
    List<Movie> findByName(String name);
    List<Movie> findByNameIgnoreCase(String name);
    List<Movie> findByNameContaining(String keyword);
    List<Movie> findByTypeAndStatus(MovieType type, Boolean status);
    List<Movie> findByReleaseYearBetween(Integer startYear, Integer endYear);
    List<Movie> findByReleaseYearIn(List<Integer>years);
    List<Movie> findByPublishedAtAfter(LocalDateTime date);
    List<Movie> findByRattingGreaterThan(Double ratting);
    List<Movie> findByTrailerURLIsNull();

    //Advanced query methods(phan trang sap xep)
    Page<Movie> findByTypeAndStatus(MovieType type, Boolean status, Pageable pageable);
    List<Movie> findByNameContainingOrderByNameDescRattingAsc(String keyword);
    List<Movie> findByNameContaining(String keyword, Sort sort);
    List<Movie> findTop3ByNameContaining(String keyword);
    List<Movie> findFirstByNameContaining(String keyword);
}
