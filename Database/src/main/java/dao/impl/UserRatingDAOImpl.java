package dao.impl;

import dao.UserRatingDAO;
import entity.User;
import entity.UserRating;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by Andrii on 28/07/2015.
 */
@Repository
public class UserRatingDAOImpl extends GenericDAOImpl<UserRating> implements UserRatingDAO {
    @Override
    public Criterion hasUser(User user) {
        return Restrictions.eq("user.id", user.getId());
    }

    @Override
    public Criterion hasAuthor(User author) {
        return Restrictions.eq("rating.author", author);
    }

    @Override
    protected void initialize(UserRating obj) {
        super.initialize(obj);
        Hibernate.initialize(obj.getUser());
    }
}
