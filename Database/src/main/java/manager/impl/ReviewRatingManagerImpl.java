package manager.impl;

import dao.GenericDAO;
import dao.ReviewRatingDAO;
import entity.Review;
import entity.ReviewRating;
import entity.User;
import manager.ReviewRatingManager;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andrii on 05/08/2015.
 */
@Service
public class ReviewRatingManagerImpl extends GenericManagerImpl<ReviewRating>
    implements ReviewRatingManager {

    @Autowired
    public ReviewRatingManagerImpl(ReviewRatingDAO dao) {
        super(ReviewRating.class, dao);
    }

    @Override
    public ReviewRatingQuery query() {
        return new ReviewRatingQueryImpl(dao);
    }

    public class ReviewRatingQueryImpl extends GenericQueryImpl<ReviewRating>
        implements ReviewRatingQuery {

        public ReviewRatingQueryImpl(GenericDAO<ReviewRating> dao) {
            super(dao);
            criteria.createAlias(criteria.getAlias() + ".review", "review", JoinType.INNER_JOIN);
            criteria.createAlias(criteria.getAlias() + ".rating", "rating", JoinType.INNER_JOIN);
            criteria.createAlias("rating.author", "author", JoinType.INNER_JOIN);
        }

        @Override
        public ReviewRatingQuery hasReview(Review review) {
            addCriterion(((ReviewRatingDAO) dao).hasReview(review));
            return this;
        }

        @Override
        public ReviewRatingQuery hasAuthor(User author) {
            addCriterion(((ReviewRatingDAO) dao).hasAuthor(author));
            return this;
        }
    }
}
