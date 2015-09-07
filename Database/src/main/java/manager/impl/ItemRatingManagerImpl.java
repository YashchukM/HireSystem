package manager.impl;

import dao.GenericDAO;
import dao.ItemRatingDAO;
import entity.Item;
import entity.ItemRating;
import entity.User;
import manager.ItemRatingManager;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andrii on 31/07/2015.
 */
@Service
public class ItemRatingManagerImpl extends GenericManagerImpl<ItemRating>
    implements ItemRatingManager {

    @Autowired
    public ItemRatingManagerImpl(ItemRatingDAO itemRatingDAO) {
        super(ItemRating.class, itemRatingDAO);
    }

    @Override
    public ItemRatingQuery query() {
        return new ItemRatingQueryImpl(dao);
    }

    public class ItemRatingQueryImpl extends GenericQueryImpl<ItemRating>
        implements ItemRatingQuery {

        public ItemRatingQueryImpl(GenericDAO<ItemRating> dao) {
            super(dao);
            criteria.createAlias(criteria.getAlias() + ".rating", "rating", JoinType.INNER_JOIN);
            criteria.createAlias("rating.author", "author", JoinType.INNER_JOIN);
            criteria.createAlias(criteria.getAlias() + ".item", "item", JoinType.INNER_JOIN);
        }

        @Override
        public ItemRatingQuery hasItem(Item item) {
            addCriterion(((ItemRatingDAO) dao).hasItem(item));
            return this;
        }

        @Override
        public ItemRatingQuery hasAuthor(User author) {
            addCriterion(((ItemRatingDAO) dao).hasAuthor(author));
            return this;
        }
    }
}
