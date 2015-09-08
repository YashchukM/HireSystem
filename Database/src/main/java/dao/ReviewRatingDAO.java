package dao;

import entity.Review;
import entity.ReviewRating;
import entity.User;
import org.hibernate.criterion.Criterion;

/**
 * Created by Andrii on 05/08/2015.
 */
public interface ReviewRatingDAO extends GenericDAO<ReviewRating> {
    public Criterion hasReview(Review review);
    public Criterion hasAuthor(User author);
}
