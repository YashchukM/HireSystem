package manager.impl;

import dao.GenericDAO;
import dao.ItemImageDAO;
import entity.Item;
import entity.ItemImage;
import manager.ItemImageManager;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andrii on 05/08/2015.
 */
@Service
public class ItemImageManagerImpl extends GenericManagerImpl<ItemImage>
    implements ItemImageManager {

    @Autowired
    public ItemImageManagerImpl(ItemImageDAO dao) {
        super(ItemImage.class, dao);
    }

    @Override
    public ItemImageQuery query() {
        return new ItemImageQueryImpl(dao);
    }

    public class ItemImageQueryImpl extends GenericQueryImpl<ItemImage>
        implements ItemImageQuery {

        public ItemImageQueryImpl(GenericDAO<ItemImage> dao) {
            super(dao);
            criteria.createAlias(criteria.getAlias() + ".itemDetails", "itemDetails", JoinType.INNER_JOIN);
        }

        @Override
        public ItemImageQuery hasItem(Item item) {
            addCriterion(((ItemImageDAO) dao).hasItem(item));
            return this;
        }
    }
}
