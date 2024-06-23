package com.example.javaspringbootb17.rest;

import com.example.javaspringbootb17.model.request.CreateMovieRequest;
import com.example.javaspringbootb17.model.request.UpdateMovieRequest;
import com.example.javaspringbootb17.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin/movies")
@RequiredArgsConstructor
public class MovieApi {
    private final MovieService movieService;
    @PostMapping
    ResponseEntity<?> createMovie(@Valid @RequestBody CreateMovieRequest createMovieRequest){
        return ResponseEntity.ok(movieService.createMovie(createMovieRequest));
    }
    @PutMapping("/{id}/update")
    ResponseEntity<?> updateMovie(@Valid @RequestBody UpdateMovieRequest updateMovieRequest,
                                  @PathVariable Integer id){
        return ResponseEntity.ok(movieService.updateMovie(updateMovieRequest,id));
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteMovie(@PathVariable Integer id){
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }
}
