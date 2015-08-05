package dao;

import entity.Item;
import entity.ItemRating;
import entity.User;
import org.hibernate.criterion.Criterion;

/**
 * Created by Andrii on 27/07/2015.
 */
public interface ItemRatingDAO extends GenericDAO<ItemRating> {
    public Criterion hasItem(Item item);
    public Criterion hasAuthor(User author);
}
