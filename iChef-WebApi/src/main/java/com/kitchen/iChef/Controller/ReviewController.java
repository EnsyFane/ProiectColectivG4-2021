package com.kitchen.iChef.Controller;

import com.kitchen.iChef.DTO.ReviewDTO;
import com.kitchen.iChef.Domain.Review;
import com.kitchen.iChef.Mapper.ReviewMapper;
import com.kitchen.iChef.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
        this.reviewMapper = new ReviewMapper();
    }

    @GetMapping
    public List<ReviewDTO> getAllReviews() {
        return reviewService.getAllReviews()
                .stream()
                .map(reviewMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public ReviewDTO getReviewById(@PathVariable String id) {
        return reviewMapper.mapToResponse(reviewService.getReview(id));
    }

    @PostMapping
    public ReviewDTO addReview(@Valid @RequestBody ReviewDTO ReviewRequest) {
        return reviewMapper.mapToResponse(reviewService.addReview(
                reviewMapper.mapFromRequest(ReviewRequest)));
    }

    @DeleteMapping(value = "/{id}")
    public ReviewDTO deleteReview(@PathVariable String id) {
        return reviewMapper.mapToResponse(reviewService.deleteReview(id));
    }

    @PutMapping(value = "/{id}")
    public ReviewDTO updateReview(@PathVariable String id, @Valid @RequestBody ReviewDTO ReviewRequest) {
        Review Review = reviewMapper.mapFromRequest(ReviewRequest);
        Review.setReviewId(id);
        return reviewMapper.mapToResponse(reviewService.updateReview(Review));
    }

    @GetMapping(value = "/rating/{recipeId}")
    public Double getRatingByRecipeId(@PathVariable String recipeId) {
        return reviewService.getRatingForRecipe(recipeId);
    }

    @GetMapping(value = "/reviews/{recipeId}")
    public List<ReviewDTO> getAllReviewsForRecipe(@PathVariable String recipeId) {
        return reviewService.getReviewsForRecipe(recipeId)
                .stream()
                .map(reviewMapper::mapToResponse)
                .collect(Collectors.toList());
    }
}
