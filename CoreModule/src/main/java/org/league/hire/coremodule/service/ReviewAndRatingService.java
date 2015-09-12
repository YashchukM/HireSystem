/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.league.hire.coremodule.service;

import entity.Item;
import entity.ItemRating;
import entity.ItemReview;
import entity.Rating;
import entity.ReviewRating;
import entity.User;
import entity.UserRating;
import entity.UserReview;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import manager.ItemRatingManager;
import manager.ItemReviewManager;
import manager.ReviewRatingManager;
import manager.UserRatingManager;
import manager.UserReviewManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexander-ilkun
 */
@Service
public class ReviewAndRatingService {
    
    @Autowired
    UserReviewManager userReviewManager;
    
    @Autowired
    ReviewRatingManager reviewRatingManager;
    
    @Autowired
    UserRatingManager userRatingManager;
    
    @Autowired
    ItemReviewManager itemReviewManager;

    @Autowired
    ItemRatingManager itemRatingManager;
    
    public List<UserReview> findAllByUser(User user) {
        return userReviewManager.query().hasUser(user).findInitialized(0, 1000);
    }

    public List<ItemReview> findAllByItem(Item item) {
        return itemReviewManager.query().hasItem(item).findInitialized(0, 1000);
    }

    public int findUserRating(User user) {
        return userRatingManager.query().hasUser(user).find(0, 1000).size();
    }
    
    public int findItemRating(Item item) {
        return itemRatingManager.query().hasItem(item).find(0, 1000).size();
    }
    
    public void saveUserReview(UserReview userReview) {
        //reviewRatingManager.save(new ReviewRating(new Rating(), userReview.getReview()));
        userReviewManager.save(userReview);
    }

    public void saveItemReview(ItemReview itemReview) {
        itemReviewManager.save(itemReview);
    }
    
    public List<Integer> findRatingsByUserReviews(List<UserReview> userReviews) {
        List<Integer> userReviewsRatings = new ArrayList<Integer>();
        for (UserReview userReview : userReviews) {
            List<ReviewRating> ratings = reviewRatingManager.query().hasReview(userReview.getReview()).find(0, 1000);
            if (ratings.isEmpty()) {
                userReviewsRatings.add(0);
            } else {
                userReviewsRatings.add(ratings.size());
            }
        }
        return userReviewsRatings;
    }

    public List<Integer> findRatingsByItemReviews(List<ItemReview> itemReviews) {
        List<Integer> itemReviewsRatings = new ArrayList<Integer>();
        for (ItemReview itemReview : itemReviews) {
            List<ReviewRating> ratings = reviewRatingManager.query().hasReview(itemReview.getReview()).find(0, 1000);
            if (ratings.isEmpty()) {
                itemReviewsRatings.add(0);
            } else {
                itemReviewsRatings.add(ratings.size());
            }
        }
        return itemReviewsRatings;
    }

    public void voteUserReview(int reviewId, User author) {
        reviewRatingManager.save(new ReviewRating(new Rating(new Date(), 1, author),
                userReviewManager.query().hasReviewId(reviewId).findInitialized(0, 1).get(0).getReview()));
    }

    public void voteItemReview(int reviewId, User author) {
        reviewRatingManager.save(new ReviewRating(new Rating(new Date(), 1, author),
                itemReviewManager.query().hasReviewId(reviewId).findInitialized(0, 1).get(0).getReview()));
    }

    public void voteUser(User user, User author) {
        userRatingManager.save(new UserRating(new Rating(new Date(), 1, author), user));
    }

    public void voteItem(Item item, User author) {
        itemRatingManager.save(new ItemRating(new Rating(new Date(), 1, author), item));
    }
}
