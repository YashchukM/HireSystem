package dao;

import entity.Item;
import entity.ItemReview;
import entity.User;
import org.hibernate.criterion.Criterion;

/**
 * Created by Andrii on 27/07/2015.
 */
public interface ItemReviewDAO extends GenericDAO<ItemReview> {
    public Criterion hasItem(Item item);
    public Criterion hasAuthor(User author);
}
