package dao;

import entity.Item;
import entity.ItemImage;
import org.hibernate.criterion.Criterion;

/**
 * Created by Andrii on 05/08/2015.
 */
public interface ItemImageDAO extends GenericDAO<ItemImage> {
    public Criterion hasItem(Item item);
}
