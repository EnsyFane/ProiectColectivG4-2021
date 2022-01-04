package com.kitchen.iChef.Repository;

import com.kitchen.iChef.Domain.Review;
import com.kitchen.iChef.Repository.Interfaces.ICrudRepository;
import com.kitchen.iChef.Repository.Interfaces.IReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ReviewRepository implements ICrudRepository<Review, String> {
    private final IReviewRepository iReviewRepository;

    @Autowired
    public ReviewRepository(IReviewRepository iReviewRepository) {
        this.iReviewRepository = iReviewRepository;
    }

    @Override
    public Review findOne(String s) {
        if (iReviewRepository.findById(s).isPresent()) {
            return iReviewRepository.findById(s).get();
        }
        return null;
    }

    @Override
    public List<Review> findAll() {
        List<Review> allReviews = new ArrayList<>();
        Iterable<Review> reviews = iReviewRepository.findAll();
        for (Review review : reviews) {
            allReviews.add(review);
        }
        return allReviews;
    }

    @Override
    public Review save(Review entity) {
        Optional<Review> review = iReviewRepository.findByRecipeIdAndUserId(entity.getRecipe().getRecipeId(), entity.getUser().getUserId());
        if (review.isPresent()) {
            entity.setReviewId(review.get().getReviewId());
            return update(entity);
        } else {
            return iReviewRepository.save(entity);
        }
    }

    @Override
    public Review delete(String s) {
        Review review = findOne(s);
        iReviewRepository.deleteById(s);
        return review;
    }

    @Override
    public Review update(Review entity) {
        Review review = findOne(entity.getReviewId());
        review.setComment(entity.getComment());
        review.setRecipe(entity.getRecipe());
        review.setUser(entity.getUser());
        review.setRecipe(entity.getRecipe());
        iReviewRepository.save(entity);
        return review;
    }

    public List<Review> findByRecipeId(String recipeId) {
        return iReviewRepository.findByRecipeId(recipeId);
    }
}
