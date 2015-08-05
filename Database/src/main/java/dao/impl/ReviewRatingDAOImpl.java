package dao.impl;

import dao.ReviewRatingDAO;
import entity.Review;
import entity.ReviewRating;
import entity.User;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by Andrii on 05/08/2015.
 */
@Repository
public class ReviewRatingDAOImpl extends GenericDAOImpl<ReviewRating>
    implements ReviewRatingDAO {
    @Override
    public Criterion hasReview(Review review) {
        return Restrictions.eq("review.id", review.getId());
    }

    @Override
    public Criterion hasAuthor(User author) {
        return Restrictions.eq("author.id", author.getId());
    }

    @Override
    protected void initialize(ReviewRating obj) {
        super.initialize(obj);
        Hibernate.initialize(obj.getReview());
    }
}
