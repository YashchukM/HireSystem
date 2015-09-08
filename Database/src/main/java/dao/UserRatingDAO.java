package dao;

import entity.User;
import entity.UserRating;
import org.hibernate.criterion.Criterion;

/**
 * Created by Andrii on 28/07/2015.
 */
public interface UserRatingDAO extends GenericDAO<UserRating> {
    public Criterion hasUser(User user);
    public Criterion hasAuthor(User author);
}
