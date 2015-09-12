package manager.impl;

import dao.GenericDAO;
import dao.UserReviewDAO;
import entity.User;
import entity.UserReview;
import manager.UserReviewManager;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andrii on 31/07/2015.
 */
@Service
public class UserReviewManagerImpl extends GenericManagerImpl<UserReview>
    implements UserReviewManager {

    @Autowired
    public UserReviewManagerImpl(UserReviewDAO userReviewDAO) {
        super(UserReview.class, userReviewDAO);
    }

    @Override
    public UserReviewQuery query() {
        return new UserReviewQueryImpl(dao);
    }

    public class UserReviewQueryImpl extends GenericQueryImpl<UserReview>
        implements UserReviewQuery {

        public UserReviewQueryImpl(GenericDAO<UserReview> dao) {
            super(dao);
            criteria.createAlias(criteria.getAlias() + ".review", "review", JoinType.INNER_JOIN);
            criteria.createAlias(criteria.getAlias() + ".user", "user", JoinType.INNER_JOIN);
        }

        @Override
        public UserReviewQuery hasUser(User user) {
            addCriterion(((UserReviewDAO) dao).hasUser(user));
            return this;
        }

        @Override
        public UserReviewQuery hasAuthor(User author) {
            addCriterion(((UserReviewDAO) dao).hasAuthor(author));
            return this;
        }
    
        @Override
        public UserReviewQuery hasReviewId(int id) {
            addCriterion(((UserReviewDAO) dao).hasReviewId(id));
            return this;
        }
    }
}
