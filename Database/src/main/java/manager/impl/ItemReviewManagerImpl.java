package manager.impl;

import dao.GenericDAO;
import dao.ItemReviewDAO;
import entity.Item;
import entity.ItemReview;
import entity.User;
import manager.ItemReviewManager;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andrii on 31/07/2015.
 */
@Service
public class ItemReviewManagerImpl extends GenericManagerImpl<ItemReview>
        implements ItemReviewManager {

    @Autowired
    public ItemReviewManagerImpl(ItemReviewDAO itemReviewDAO) {
        super(ItemReview.class, itemReviewDAO);
    }

    @Override
    public ItemReviewQuery query() {
        return new ItemReviewQueryImpl(dao);
    }

    public class ItemReviewQueryImpl extends GenericQueryImpl<ItemReview>
        implements ItemReviewQuery {

        public ItemReviewQueryImpl(GenericDAO<ItemReview> dao) {
            super(dao);
            criteria.createAlias(criteria.getAlias() + ".review", "review", JoinType.INNER_JOIN);
            criteria.createAlias("review.author", "author", JoinType.INNER_JOIN);
            criteria.createAlias(criteria.getAlias() + ".item", "item", JoinType.INNER_JOIN);
        }

        @Override
        public ItemReviewQuery hasItem(Item item) {
            addCriterion(((ItemReviewDAO) dao).hasItem(item));
            return this;
        }

        @Override
        public ItemReviewQuery hasAuthor(User author) {
            addCriterion(((ItemReviewDAO) dao).hasAuthor(author));
            return this;
        }
    }
}
