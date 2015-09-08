package dao.impl;

import dao.UserDetailsDAO;
import entity.User;
import entity.UserDetails;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by Andrii on 28/07/2015.
 */
@Repository
public class UserDetailsDAOImpl extends GenericDAOImpl<UserDetails> implements UserDetailsDAO {
    @Override
    public Criterion hasUser(User user) {
        return Restrictions.eq("user.id", user.getId());
    }

    @Override
    protected void initialize(UserDetails obj) {
        super.initialize(obj);
        Hibernate.initialize(obj.getUser());
    }
}
