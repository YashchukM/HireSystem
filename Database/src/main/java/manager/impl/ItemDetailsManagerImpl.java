package manager.impl;

import dao.GenericDAO;
import dao.ItemDetailsDAO;
import entity.Item;
import entity.ItemDetails;
import manager.ItemDetailsManager;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andrii on 31/07/2015.
 */
@Service
public class ItemDetailsManagerImpl extends GenericManagerImpl<ItemDetails>
    implements ItemDetailsManager {

    @Autowired
    public ItemDetailsManagerImpl(ItemDetailsDAO itemDetailsDAO) {
        super(ItemDetails.class, itemDetailsDAO);
    }

    @Override
    public ItemDetailsQuery query() {
        return new ItemDetailsQueryImpl(dao);
    }

    public class ItemDetailsQueryImpl extends GenericQueryImpl<ItemDetails>
        implements ItemDetailsQuery {

        public ItemDetailsQueryImpl(GenericDAO<ItemDetails> dao) {
            super(dao);
            criteria.createAlias(criteria.getAlias() + ".item", "item", JoinType.INNER_JOIN);
        }

        @Override
        public ItemDetailsQuery hasItem(Item item) {
            addCriterion(((ItemDetailsDAO) dao).hasItem(item));
            return this;
        }
    }
}
