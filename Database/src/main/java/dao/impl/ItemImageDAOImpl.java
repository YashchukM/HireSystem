package dao.impl;

import dao.ItemImageDAO;
import entity.Item;
import entity.ItemImage;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by Andrii on 05/08/2015.
 */
@Repository
public class ItemImageDAOImpl extends GenericDAOImpl<ItemImage>
    implements ItemImageDAO {
    @Override
    public Criterion hasItem(Item item) {
        return Restrictions.eq("itemDetails.id", item.getId());
    }

    @Override
    protected void initialize(ItemImage obj) {
        super.initialize(obj);
        Hibernate.initialize(obj.getItemDetails());
    }
}
