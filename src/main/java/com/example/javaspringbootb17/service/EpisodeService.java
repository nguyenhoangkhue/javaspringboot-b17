package com.example.javaspringbootb17.service;

import com.example.javaspringbootb17.entity.Episode;
import com.example.javaspringbootb17.entity.Movie;
import com.example.javaspringbootb17.exception.ResourceNotFoundException;
import com.example.javaspringbootb17.model.request.CreateEpisodeRequest;
import com.example.javaspringbootb17.repsitory.EpisodeRepository;
import com.example.javaspringbootb17.repsitory.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EpisodeService {
    private final EpisodeRepository episodeRepository;
    private final MovieRepository movieRepository;
    private final CloudinaryService cloudinaryService;

    public List<Episode> getEpisodeListOfMovie(Integer movieId) {
        return episodeRepository.findByMovie_IdAndStatusOrderByDisplayOrderAsc(movieId, true);
    }

    public List<Episode> getEpisodeListOfMovieByAdmin(Integer movieId) {
        return episodeRepository.findByMovie_IdOrderByDisplayOrderAsc(movieId);
    }

    public Episode getEpisodeByDisplayOrder(Integer movieId, String tap) {
        Integer covertTap = tap.equals("full") ? 1 : Integer.parseInt(tap);
        return episodeRepository
                .findByMovie_IdAndStatusAndDisplayOrder(movieId, true, covertTap)
                .orElse(null);
    }

    public Episode uploadVideo(Integer id, MultipartFile file) {
        Episode episode = episodeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tập phim có id = " + id));

        try {
            Map result = cloudinaryService.uploadVideo(file);
            episode.setVideoUrl((String) result.get("url"));
            Double duration = (Double) result.get("duration");
            episode.setDuration(duration.intValue());
            return episodeRepository.save(episode);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi upload video");
        }
    }
    public Episode createEpisode(Integer id, CreateEpisodeRequest request){
        Movie movie = movieRepository.findMovieById(id).orElseThrow(()->new ResourceNotFoundException("Movie not found"));
        Episode episode=Episode.builder()
                .name(request.getName())
                .displayOrder(request.getDisplayOrder())
                .status(request.getStatus())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return episodeRepository.save(episode);
    }
    public void deleteEpisode(Integer id) {
        Episode episode=episodeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Episode not found"));
        episodeRepository.deleteById(id);
    }
}
