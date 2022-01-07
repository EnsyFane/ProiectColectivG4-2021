package com.kitchen.iChef.Service;

import com.kitchen.iChef.Domain.Review;
import com.kitchen.iChef.Exceptions.ResourceNotFoundException;
import com.kitchen.iChef.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review getReview(String id) {
        Review review;
        try {
            review = reviewRepository.findOne(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("No review with this id");
        }
        return review;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review deleteReview(String id) {
        Review review;
        try {
            review = reviewRepository.delete(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("No review with this id");
        }
        return review;
    }

    public Review updateReview(Review review) {
        Review updatedReview;
        try {
            updatedReview = reviewRepository.update(review);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("No review with this id");
        }
        return updatedReview;
    }

    public Double getRatingForRecipe(String recipeId) {
        OptionalDouble rating = reviewRepository.findByRecipeId(recipeId)
                .stream().mapToDouble(Review::getRating).filter(t -> t != -1).average();
        if (rating.isPresent()) {
            return rating.getAsDouble();
        }
        return 0d;
    }

    public List<Review> getReviewsForRecipe(String recipeId) {
        return reviewRepository.findByRecipeId(recipeId);
    }
}
