package com.example.javaspringbootb17.service;

import com.example.javaspringbootb17.entity.Episode;
import com.example.javaspringbootb17.entity.Movie;
import com.example.javaspringbootb17.entity.Review;
import com.example.javaspringbootb17.model.enums.MovieType;
import com.example.javaspringbootb17.repsitory.EpisodeRepository;
import com.example.javaspringbootb17.repsitory.MovieRepository;
import com.example.javaspringbootb17.repsitory.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WebService {
    private final MovieRepository movieRepository;
    private final EpisodeRepository episodeRepository;
    private final ReviewRepository reviewRepository;
    public Page<Movie> findByType(MovieType type, Boolean status, Integer page, Integer limit){
        Pageable pageable= PageRequest.of(page-1,limit, Sort.by("publishedAt").descending());
        return movieRepository.findByTypeAndStatus(type,status,pageable);
    }
    public List<Movie>getHotMovie(){
        return movieRepository.findTop10ByStatusOrderByRatingDesc(true);
    }
    public Movie getMovieDetail(Integer id,String slug){
        return movieRepository.findByStatusAndIdAndSlug(true,id,slug).orElse(null);
    }
    public List<Movie>getRelateMovies(Movie movie){
        return movieRepository.findTop6ByTypeAndStatusAndIdNotOrderByRatingDesc(movie.getType(),true, movie.getId());
    }
    public List<Episode>getEpisodes(Movie movie){
        return episodeRepository.findByMovie_IdAndStatusOrderByDisplayOrderDesc(movie.getId(),true);
    }
    public List<Review>getReviews(Movie movie){
        return reviewRepository.findAllByMovie_Id(movie.getId());
    }
}
