package manager;

import entity.Review;
import entity.ReviewRating;
import entity.User;

/**
 * Created by Andrii on 05/08/2015.
 */
public interface ReviewRatingManager extends GenericManager<ReviewRating> {
    @Override
    public ReviewRatingQuery query();

    public interface ReviewRatingQuery extends GenericQuery<ReviewRating> {
        public ReviewRatingQuery hasReview(Review review);
        public ReviewRatingQuery hasAuthor(User author);
    }
}
