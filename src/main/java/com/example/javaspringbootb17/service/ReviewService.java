package com.example.javaspringbootb17.service;

import com.example.javaspringbootb17.entity.Movie;
import com.example.javaspringbootb17.entity.Review;
import com.example.javaspringbootb17.entity.User;
import com.example.javaspringbootb17.exception.BadRequestException;
import com.example.javaspringbootb17.exception.ResourceNotFoundException;
import com.example.javaspringbootb17.model.request.CreateReviewRequest;
import com.example.javaspringbootb17.model.request.UpdateReviewRequest;
import com.example.javaspringbootb17.repsitory.MovieRepository;
import com.example.javaspringbootb17.repsitory.ReviewRepository;
import com.example.javaspringbootb17.repsitory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    public List<Review>getReviewsByMovie(Integer id){
        return reviewRepository.findAllByMovie_IdOrderByCreatedAtDesc(id);
    }
    //TODO:Validation huong dan sau(StringBoot Validation)
    public Review createReview(CreateReviewRequest request) {
        //TODO:Fix user. Ve sau user chinh la user dang dang nhap
        Integer userId=1;
        User user=userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found"));
        Movie movie=movieRepository.findById(request.getMovieId())
                .orElseThrow(()->new ResourceNotFoundException("Movie not found"));
        Review review=Review.builder()
                .content(request.getContent())
                .rating(Double.valueOf(request.getRating()))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .user(user)
                .movie(movie)
                .build();
        reviewRepository.save(review);
        return review;
    }

    public Review updateReview(Integer id, UpdateReviewRequest request) {
        Review review=reviewRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Review not found"));
        Integer userId=1;
        User user=userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found"));
        if (!review.getUser().getId().equals(user.getId())){
            throw new RuntimeException("You can't update this review");
        }
        review.setContent(request.getContent());
        review.setRating(Double.valueOf(request.getRating()));
        review.setUpdatedAt(LocalDateTime.now());
        reviewRepository.save(review);
        return review;
    }

    public void deleteReview(Integer id) {
        Review review=reviewRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Review not found"));
        Integer userId=1;
        User user=userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found"));
        if (!review.getUser().getId().equals(user.getId())){
            throw new BadRequestException("You can't update this review");
        }
        reviewRepository.deleteById(id);
    }
}
