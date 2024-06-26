package com.example.javaspringbootb17.rest;

import com.example.javaspringbootb17.model.request.CreateEpisodeRequest;
import com.example.javaspringbootb17.service.EpisodeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/admin/episodes")
@RequiredArgsConstructor
public class EpisodeApi {
    private final EpisodeService episodeService;

    @PostMapping("/{id}/upload-video")
    ResponseEntity<?> uploadVideo(@PathVariable Integer id, @RequestParam MultipartFile file) {
        return ResponseEntity.ok(episodeService.uploadVideo(id, file));
    }
    @PostMapping("/{id}/create")
    ResponseEntity<?> createEpisode(@Valid @PathVariable Integer id,
                                    @RequestParam CreateEpisodeRequest request) {
        return ResponseEntity.ok(episodeService.createEpisode(id,request));
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteEpisode(@PathVariable Integer id){
        episodeService.deleteEpisode(id);
        return ResponseEntity.ok().build();
    }
}