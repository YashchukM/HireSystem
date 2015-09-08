package dao.impl;

import dao.ItemRatingDAO;
import entity.Item;
import entity.ItemRating;
import entity.User;
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
public class ItemRatingDAOImpl extends GenericDAOImpl<ItemRating> implements ItemRatingDAO {
    @Override
    public Criterion hasItem(Item item) {
        return Restrictions.eq("item.id", item.getId());
    }

    @Override
    public Criterion hasAuthor(User author) {
        return Restrictions.eq("author.id", author.getId());
    }

    @Override
    protected void initialize(ItemRating itemRating) {
        super.initialize(itemRating);
        Hibernate.initialize(itemRating.getItem());
    }
}
