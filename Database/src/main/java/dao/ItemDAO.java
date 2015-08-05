package dao;

import entity.Category;
import entity.Item;
import entity.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

/**
 * Created by Andrii on 22/07/2015.
 */
public interface ItemDAO extends GenericDAO<Item> {
    public Criterion hasNameLike(String name);
    public Criterion hasCategory(Category category);
    public Criterion hasPriceBetween(float minPrice, float maxPrice);
    public Criterion hasHired(boolean isHired);
    public Criterion hasOwner(User owner);
}
