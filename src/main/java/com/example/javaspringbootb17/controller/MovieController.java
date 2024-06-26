package com.example.javaspringbootb17.controller;

import com.example.javaspringbootb17.entity.Movie;
import com.example.javaspringbootb17.repsitory.*;
import com.example.javaspringbootb17.service.EpisodeService;
import com.example.javaspringbootb17.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final DirectorRepository directorRepository;
    private final ActorRepository actorRepository;
    private final GenreRepository genreRepository;
    private final CountryRepository countryRepository;
    private final EpisodeService episodeService;
    @GetMapping("/admin/movies")
    public String movies(Model model) {
        List<Movie> movies=movieService.getAllMovies();
        model.addAttribute("movies",movies);
        return "admin/movie/index";
    }

    @GetMapping("/admin/movies/create")
    public String create(Model model) {
        model.addAttribute("directors",directorRepository.findAll());
        model.addAttribute("actors",actorRepository.findAll());
        model.addAttribute("genres",genreRepository.findAll());
        model.addAttribute("countries",countryRepository.findAll());
        return "admin/movie/create";
    }

    @GetMapping("/admin/movies/{id}/detail")
    public String detail(@PathVariable Integer id,
                         Model model) {
        model.addAttribute("directors",directorRepository.findAll());
        model.addAttribute("actors",actorRepository.findAll());
        model.addAttribute("genres",genreRepository.findAll());
        model.addAttribute("countries",countryRepository.findAll());
        model.addAttribute("episodes", episodeService.getEpisodeListOfMovieByAdmin(id));
        Movie movie=movieService.getMovieDetail(id);
        model.addAttribute("movie",movie);
        return "admin/movie/detail";
    }

}

