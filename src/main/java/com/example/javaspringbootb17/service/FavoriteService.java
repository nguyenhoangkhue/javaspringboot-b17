package com.example.javaspringbootb17.service;

import com.example.javaspringbootb17.entity.Favorite;
import com.example.javaspringbootb17.entity.Movie;
import com.example.javaspringbootb17.entity.User;
import com.example.javaspringbootb17.exception.BadRequestException;
import com.example.javaspringbootb17.exception.ResourceNotFoundException;
import com.example.javaspringbootb17.repsitory.FavoriteRepository;
import com.example.javaspringbootb17.repsitory.MovieRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final MovieRepository movieRepository;
    private final HttpSession session;

    public Favorite addToFavorite(Integer movieId) {
        User user = (User) session.getAttribute("currentUser");
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        if (favoriteRepository.existsByUser_IdAndMovie_Id(user.getId(), movie.getId())) {
            throw new BadRequestException("Movie already in favorite list");
        }
        Favorite favorite = Favorite.builder()
                .user(user)
                .movie(movie)
                .createdAt(LocalDateTime.now())
                .build();
        return favoriteRepository.save(favorite);
    }

    public void deleteFromFavorite(Integer movieId) {
        User user = (User) session.getAttribute("currentUser");
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));

        Optional<Favorite> favoriteOptional = favoriteRepository.findByUser_IdAndMovie_Id(user.getId(), movie.getId());
        if (favoriteOptional.isEmpty()) {
            throw new ResourceNotFoundException("Movie not in favorite list");
        }

        favoriteRepository.delete(favoriteOptional.get());
    }

    public boolean isFavorite(Integer movieId) {
        User user = (User) session.getAttribute("currentUser");

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));

        return favoriteRepository.existsByUser_IdAndMovie_Id(user.getId(), movie.getId());
    }

    public List<Favorite> getAllFavoritesByCurrentUser() {
        User user = (User) session.getAttribute("currentUser");
        return favoriteRepository.findByUser_IdOrderByCreatedAtDesc(user.getId());
    }
}