package dao;

import entity.User;
import entity.UserReview;
import org.hibernate.criterion.Criterion;

/**
 * Created by Andrii on 28/07/2015.
 */
public interface UserReviewDAO extends GenericDAO<UserReview> {
    public Criterion hasUser(User user);
    public Criterion hasAuthor(User author);
    public Criterion hasReviewId(int id);
}
