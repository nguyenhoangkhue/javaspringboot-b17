package com.example.javaspringbootb17.service;

import com.example.javaspringbootb17.entity.Country;
import com.example.javaspringbootb17.entity.Movie;
import com.example.javaspringbootb17.entity.Review;
import com.example.javaspringbootb17.entity.User;
import com.example.javaspringbootb17.exception.BadRequestException;
import com.example.javaspringbootb17.exception.ResourceNotFoundException;
import com.example.javaspringbootb17.model.enums.MovieType;
import com.example.javaspringbootb17.model.request.CreateMovieRequest;
import com.example.javaspringbootb17.model.request.UpdateMovieRequest;
import com.example.javaspringbootb17.repsitory.*;
import com.github.slugify.Slugify;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final CountryRepository countryRepository;
    private final GenreRepository genreRepository;
    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;
    Slugify slugify= Slugify.builder().build();
    public List<Movie>getAllMovies(){
        return movieRepository.findAll(Sort.by(("createdAt")).descending());
    }
    public Movie getMovieDetail(Integer id){
        return movieRepository.findMovieById(id).orElse(null);
    }
    public Movie createMovie(CreateMovieRequest request){
        Country country=countryRepository.findById(request.getCountryId())
                .orElseThrow(()->new ResourceNotFoundException("Quốc gia không tồn tại"));
        Movie movie=Movie.builder()
                .name(request.getName())
                .slug(slugify.slugify(request.getName()))
                .description(request.getDescription())
                .poster("https://placehold.co/600x400?text="+request.getName().substring(0,1).toLowerCase())
                .releaseYear(request.getReleaseYear())
                .trailerURL(request.getTrailerURL())
                .type(request.getType())
                .status(request.getStatus())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .publishedAt(request.getStatus()?LocalDateTime.now():null)
                .country(country)
                .genres(genreRepository.findAllById(request.getGenreIds()))
                .actors(actorRepository.findAllById(request.getActorIds()))
                .directors(directorRepository.findAllById(request.getDirectorIds()))
                .build();
        return movieRepository.save(movie);
    }
    public Movie updateMovie(UpdateMovieRequest request, Integer id){
        Country country=countryRepository.findById(request.getCountryId())
                .orElseThrow(()->new ResourceNotFoundException("Quốc gia không tồn tại"));
        Movie movie = movieRepository.findMovieById(id).orElseThrow(()->new ResourceNotFoundException("Movie not found"));
        movie.setName(request.getName());
        movie.setTrailerURL(request.getTrailerURL());
        movie.setDescription(request.getDescription());
        movie.setReleaseYear(request.getReleaseYear());
        movie.setType(request.getType());
        movie.setStatus(request.getStatus());
        movie.setCountry(country);
        movie.setSlug(slugify.slugify(request.getName()));

        return movieRepository.save(movie);
    }
    public void deleteMovie(Integer id) {
        Movie movie=movieRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Movie not found"));
        movieRepository.deleteById(id);
    }
}

