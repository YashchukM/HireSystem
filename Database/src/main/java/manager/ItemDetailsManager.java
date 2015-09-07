package manager;

import entity.Item;
import entity.ItemDetails;

/**
 * Created by Andrii on 31/07/2015.
 */
public interface ItemDetailsManager extends GenericManager<ItemDetails> {
    @Override
    public ItemDetailsQuery query();

    public interface ItemDetailsQuery extends GenericQuery<ItemDetails> {
        public ItemDetailsQuery hasItem(Item item);
    }
}
