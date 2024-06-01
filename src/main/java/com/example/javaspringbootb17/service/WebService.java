package com.example.javaspringbootb17.service;

import com.example.javaspringbootb17.entity.Movie;
import com.example.javaspringbootb17.model.enums.MovieType;
import com.example.javaspringbootb17.repsitory.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebService {
    private final MovieRepository movieRepository;
    public Page<Movie> findByType(MovieType type, Boolean status, Integer page, Integer limit){
        Pageable pageable= PageRequest.of(page-1,limit, Sort.by("publishedAt").descending());
        return movieRepository.findByTypeAndStatus(type,status,pageable);
    }
}
