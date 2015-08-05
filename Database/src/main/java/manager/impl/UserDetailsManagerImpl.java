package manager.impl;

import dao.GenericDAO;
import dao.UserDetailsDAO;
import entity.User;
import entity.UserDetails;
import manager.UserDetailsManager;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andrii on 31/07/2015.
 */
@Service
public class UserDetailsManagerImpl extends GenericManagerImpl<UserDetails>
        implements UserDetailsManager {

    @Autowired
    public UserDetailsManagerImpl(UserDetailsDAO userDetailsDAO) {
        super(UserDetails.class, userDetailsDAO);
    }

    @Override
    public UserDetailsQuery query() {
        return new UserDetailsQueryImpl(dao);
    }

    public class UserDetailsQueryImpl extends GenericQueryImpl<UserDetails> implements UserDetailsQuery {

        public UserDetailsQueryImpl(GenericDAO<UserDetails> dao) {
            super(dao);
            criteria.createAlias(criteria.getAlias() + ".user", "user", JoinType.INNER_JOIN);
        }

        @Override
        public UserDetailsQuery hasUser(User user) {
            addCriterion(((UserDetailsDAO) dao).hasUser(user));
            return this;
        }
    }
}
