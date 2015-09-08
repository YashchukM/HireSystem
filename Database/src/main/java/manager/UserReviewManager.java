package manager;

import entity.User;
import entity.UserReview;

/**
 * Created by Andrii on 31/07/2015.
 */
public interface UserReviewManager extends GenericManager<UserReview> {
    @Override
    public UserReviewQuery query();

    public interface UserReviewQuery extends GenericQuery<UserReview> {
        public UserReviewQuery hasUser(User user);
        public UserReviewQuery hasAuthor(User author);
    }
}
