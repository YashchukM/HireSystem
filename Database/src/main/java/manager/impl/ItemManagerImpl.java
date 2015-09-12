package manager.impl;

import dao.GenericDAO;
import dao.ItemDAO;
import entity.Category;
import entity.Item;
import entity.User;
import manager.ItemManager;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andrii on 31/07/2015.
 */
@Service
public class ItemManagerImpl extends GenericManagerImpl<Item>
        implements ItemManager {

    @Autowired
    public ItemManagerImpl(ItemDAO itemDAO) {
        super(Item.class, itemDAO);
    }

    @Override
    public ItemQuery query() {
        return new ItemQueryImpl(dao);
    }

    public class ItemQueryImpl extends GenericQueryImpl<Item>
            implements ItemQuery {

        public ItemQueryImpl(GenericDAO<Item> dao) {
            super(dao);
//            criteria.createAlias(criteria.getAlias() + ".itemDetails", "itemDetails", JoinType.INNER_JOIN);
//            criteria.createAlias(criteria.getAlias() + ".category", "category", JoinType.INNER_JOIN);
//            criteria.createAlias("itemDetails.owner", "owner", JoinType.INNER_JOIN);
        }

        @Override
        public ItemQuery hasNameLike(String name) {
            addCriterion(((ItemDAO) dao).hasNameLike(name));
            return this;
        }

        @Override
        public ItemQuery hasCategory(Category category) {
            addCriterion(((ItemDAO) dao).hasCategory(category));
            return this;
        }

        @Override
        public ItemQuery hasPriceBetween(float lo, float hi) {
            addCriterion(((ItemDAO) dao).hasPriceBetween(lo, hi));
            return this;
        }

        @Override
        public ItemQuery hasHired(boolean isHired) {
            addCriterion(((ItemDAO) dao).hasHired(isHired));
            return this;
        }

        @Override
        public ItemQuery hasOwner(User owner) {
            addCriterion(((ItemDAO) dao).hasOwner(owner));
            return this;
        }
    }
}
