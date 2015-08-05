package dao.impl;

import dao.ItemDetailsDAO;
import entity.Item;
import entity.ItemDetails;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Andrii on 28/07/2015.
 */
@Repository
public class ItemDetailsDAOImpl extends GenericDAOImpl<ItemDetails> implements ItemDetailsDAO {
    @Override
    public Criterion hasItem(Item item) {
        return Restrictions.eq("item.id", item.getId());
    }

    @Override
    protected void initialize(ItemDetails itemDetails) {
        super.initialize(itemDetails);
        Hibernate.initialize(itemDetails.getItem());
        Hibernate.initialize(itemDetails.getImages());
    }
}
