package dao;

import entity.Item;
import entity.ItemDetails;
import org.hibernate.criterion.Criterion;

/**
 * Created by Andrii on 27/07/2015.
 */
public interface ItemDetailsDAO extends GenericDAO<ItemDetails> {
    public Criterion hasItem(Item item);
}
