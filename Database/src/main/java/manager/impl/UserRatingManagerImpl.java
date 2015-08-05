package manager.impl;

import dao.GenericDAO;
import dao.UserRatingDAO;
import entity.User;
import entity.UserRating;
import manager.UserRatingManager;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andrii on 31/07/2015.
 */
@Service
public class UserRatingManagerImpl extends GenericManagerImpl<UserRating>
    implements UserRatingManager {

    @Autowired
    public UserRatingManagerImpl(UserRatingDAO userRatingDAO) {
        super(UserRating.class, userRatingDAO);
    }

    @Override
    public UserRatingQuery query() {
        return new UserRatingQueryImpl(dao);
    }

    public class UserRatingQueryImpl extends GenericQueryImpl<UserRating>
            implements UserRatingQuery {

        public UserRatingQueryImpl(GenericDAO<UserRating> dao) {
            super(dao);
            criteria.createAlias(criteria.getAlias() + ".rating", "rating", JoinType.INNER_JOIN);
            criteria.createAlias(criteria.getAlias() + ".user", "user", JoinType.INNER_JOIN);
        }

        @Override
        public UserRatingQuery hasUser(User user) {
            addCriterion(((UserRatingDAO) dao).hasUser(user));
            return this;
        }

        @Override
        public UserRatingQuery hasAuthor(User author) {
            addCriterion(((UserRatingDAO) dao).hasAuthor(author));
            return this;
        }
    }
}
