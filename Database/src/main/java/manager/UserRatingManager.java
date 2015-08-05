package manager;

import entity.User;
import entity.UserRating;

/**
 * Created by Andrii on 31/07/2015.
 */
public interface UserRatingManager extends GenericManager<UserRating> {
    @Override
    public UserRatingQuery query();

    public interface UserRatingQuery extends GenericQuery<UserRating> {
        public UserRatingQuery hasUser(User user);
        public UserRatingQuery hasAuthor(User author);
    }
}
