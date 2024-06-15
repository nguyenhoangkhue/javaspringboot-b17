package com.example.javaspringbootb17.rest;


import com.example.javaspringbootb17.entity.Favorite;
import com.example.javaspringbootb17.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/favorites")
@RequiredArgsConstructor
public class FavoriteApi {
    private final FavoriteService favoriteService;
    // POST: http://localhost:8080/api/favorites?movieId=1
    @PostMapping
    public ResponseEntity<?> addToFavorite(@RequestParam Integer movieId) {
        Favorite favorite = favoriteService.addToFavorite(movieId);
        return ResponseEntity.ok(favorite);
    }

    // DELETE: http://localhost:8080/api/favorites?movieId=1
    @DeleteMapping
    public ResponseEntity<?> deleteFromFavorite(@RequestParam Integer movieId) {
        favoriteService.deleteFromFavorite(movieId);
        return ResponseEntity.ok().build();
    }
}
