package dao.impl;

import dao.UserDAO;
import entity.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Andrii on 28/07/2015.
 */
@Repository
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {
    @Override
    public Criterion hasLogin(String login) {
        return Restrictions.eq("login", login);
    }

    @Override
    public Criterion hasNameLike(String name) {
        return Restrictions.ilike("userDetails.name", name, MatchMode.ANYWHERE);
    }

    @Override
    public Criterion hasSurnameLike(String surname) {
        return Restrictions.ilike("userDetails.surname", surname, MatchMode.ANYWHERE);
    }

    @Override
    public Criterion hasBirthDateBetween(Date lo, Date hi) {
        return Restrictions.between("userDetails.birthDate", lo, hi);
    }

    @Override
    public Criterion hasEmailLike(String email) {
        return Restrictions.ilike("userDetails.email", email, MatchMode.ANYWHERE);
    }

    @Override
    public Criterion hasPhoneLike(String phone) {
        return Restrictions.ilike("userDetails.phone", phone, MatchMode.ANYWHERE);
    }

    @Override
    protected void initialize(User user) {
        super.initialize(user);
        Hibernate.initialize(user.getUserDetails());
    }
}
