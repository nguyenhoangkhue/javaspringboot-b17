package com.example.javaspringbootb17.rest;

import com.example.javaspringbootb17.entity.Review;
import com.example.javaspringbootb17.model.request.CreateReviewRequest;
import com.example.javaspringbootb17.model.request.UpdateReviewRequest;
import com.example.javaspringbootb17.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/reviews")
public class ReviewApi {
    private final ReviewService reviewService;
    //tao review-post
    @PostMapping
    public ResponseEntity<?> crateReview(@RequestBody CreateReviewRequest request){
        Review review=reviewService.createReview(request);
        return ResponseEntity.ok(review);
    }
    //cap nhat review-put
    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@PathVariable Integer id,@RequestBody UpdateReviewRequest request){
        Review review=reviewService.updateReview(id,request);
        return ResponseEntity.ok(review);
    }
    // xoa review-delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer id){
        reviewService.deleteReview(id);
        return ResponseEntity.ok().build();
    }
}
