package dao.impl;

import dao.ItemReviewDAO;
import entity.Item;
import entity.ItemReview;
import entity.User;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by Andrii on 28/07/2015.
 */
@Repository
public class ItemReviewDAOImpl extends GenericDAOImpl<ItemReview> implements ItemReviewDAO {
    @Override
    public Criterion hasItem(Item item) {
        return Restrictions.eq("item.id", item.getId());
    }

    @Override
    public Criterion hasAuthor(User author) {
        return Restrictions.eq("author.id", author.getId());
    }

    @Override
    public Criterion hasReviewId(int id) {
        return Restrictions.eq("review.id", id);
    }
    
    @Override
    protected void initialize(ItemReview obj) {
        super.initialize(obj);
        Hibernate.initialize(obj.getItem());
    }
}
