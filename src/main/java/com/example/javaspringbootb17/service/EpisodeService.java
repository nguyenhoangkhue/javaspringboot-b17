package com.example.javaspringbootb17.service;

import com.example.javaspringbootb17.entity.Episode;
import com.example.javaspringbootb17.entity.Movie;
import com.example.javaspringbootb17.exception.BadRequestException;
import com.example.javaspringbootb17.exception.ResourceNotFoundException;
import com.example.javaspringbootb17.model.request.CreateEpisodeRequest;
import com.example.javaspringbootb17.model.request.UpdateEpisodeRequest;
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
    public Episode createEpisode(CreateEpisodeRequest request) {
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phim với id " + request.getMovieId()));

        if (episodeRepository.existsByMovie_IdAndDisplayOrder(movie.getId(), request.getDisplayOrder())) {
            throw new BadRequestException("Thứ tự tập phim không được trùng nhau");
        }

        Episode episode = Episode.builder()
                .name(request.getName())
                .displayOrder(request.getDisplayOrder())
                .status(request.getStatus())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .publishedAt(request.getStatus() ? LocalDateTime.now() : null)
                .movie(movie)
                .build();
        return episodeRepository.save(episode);
    }

    public Episode updateEpisode(Integer id, UpdateEpisodeRequest request) {
        Episode episode = episodeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tập phim có id = " + id));

        if (!episode.getDisplayOrder().equals(request.getDisplayOrder()) && episodeRepository.existsByMovie_IdAndDisplayOrder(episode.getMovie().getId(), request.getDisplayOrder())) {
            throw new BadRequestException("Thứ tự tập phim không được trùng nhau");
        }

        episode.setName(request.getName());
        episode.setDisplayOrder(request.getDisplayOrder());
        episode.setStatus(request.getStatus());
        episode.setUpdatedAt(LocalDateTime.now());
        episode.setPublishedAt(request.getStatus() ? LocalDateTime.now() : null);
        return episodeRepository.save(episode);
    }

    public void deleteEpisode(Integer id) {
        Episode episode = episodeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tập phim có id = " + id));

        // TODO: Xóa video trên cloudinary
        episodeRepository.delete(episode);
    }
}
