package dao.impl;

import dao.ItemDAO;
import entity.Category;
import entity.Item;
import entity.User;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Andrii on 22/07/2015.
 */
@Repository
public class ItemDAOImpl extends GenericDAOImpl<Item> implements ItemDAO {
    @Override
    public Criterion hasNameLike(String name) {
        return Restrictions.ilike("name", name, MatchMode.ANYWHERE);
    }

    @Override
    public Criterion hasCategory(Category category) {
        return Restrictions.eq("category.id", category.getId());
    }

    @Override
    public Criterion hasPriceBetween(float lo, float hi) {
        return Restrictions.between("price", lo, hi);
    }

    @Override
    public Criterion hasHired(boolean isHired) {
        return Restrictions.eq("isHired", isHired);
    }

    @Override
    public Criterion hasOwner(User owner) {
        return Restrictions.eq("owner.id", owner.getId());
    }

    @Override
    protected void initialize(Item item) {
        super.initialize(item);
        Hibernate.initialize(item.getCategory());
        Hibernate.initialize(item.getItemDetails());
    }


}
