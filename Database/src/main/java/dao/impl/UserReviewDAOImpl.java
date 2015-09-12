package dao.impl;

import dao.UserReviewDAO;
import entity.User;
import entity.UserReview;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by Andrii on 28/07/2015.
 */
@Repository
public class UserReviewDAOImpl extends GenericDAOImpl<UserReview> implements UserReviewDAO {
    @Override
    public Criterion hasUser(User user) {
        return Restrictions.eq("user.id", user.getId());
    }

    @Override
    public Criterion hasAuthor(User author) {
        return Restrictions.eq("review.author", author);
    }

    @Override
    public Criterion hasReviewId(int id) {
        return Restrictions.eq("review.id", id);
    }
    
    @Override
    protected void initialize(UserReview obj) {
        super.initialize(obj);
        Hibernate.initialize(obj.getUser());
    }
}
