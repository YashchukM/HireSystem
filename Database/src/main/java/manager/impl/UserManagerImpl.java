package manager.impl;

import dao.GenericDAO;
import dao.UserDAO;
import entity.User;
import manager.UserManager;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Andrii on 31/07/2015.
 */
@Service
public class UserManagerImpl extends GenericManagerImpl<User>
        implements UserManager {

    @Autowired
    public UserManagerImpl(UserDAO userDAO) {
        super(User.class, userDAO);
    }

    @Override
    public UserQuery query() {
        return new UserQueryImpl(dao);
    }

    public class UserQueryImpl extends GenericQueryImpl<User> implements UserQuery {

        public UserQueryImpl(GenericDAO<User> dao) {
            super(dao);
            //criteria.createAlias(criteria.getAlias() + ".userDetails", "userDetails", JoinType.INNER_JOIN);
        }

        @Override
        public UserQuery hasLogin(String login) {
            addCriterion(((UserDAO) dao).hasLogin(login));
            return this;
        }

        @Override
        public UserQuery hasNameLike(String name) {
            addCriterion(((UserDAO) dao).hasNameLike(name));
            return this;
        }

        @Override
        public UserQuery hasSurnameLike(String surname) {
            addCriterion(((UserDAO) dao).hasSurnameLike(surname));
            return this;
        }

        @Override
        public UserQuery hasBirthDateBetween(Date lo, Date hi) {
            addCriterion(((UserDAO) dao).hasBirthDateBetween(lo, hi));
            return this;
        }

        @Override
        public UserQuery hasEmailLike(String email) {
            addCriterion(((UserDAO) dao).hasEmailLike(email));
            return this;
        }

        @Override
        public UserQuery hasPhoneLike(String phone) {
            addCriterion(((UserDAO) dao).hasPhoneLike(phone));
            return this;
        }
    }
}
