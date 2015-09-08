package manager;

import entity.Item;
import entity.ItemImage;

/**
 * Created by Andrii on 05/08/2015.
 */
public interface ItemImageManager extends GenericManager<ItemImage> {
    @Override
    public ItemImageQuery query();

    public interface ItemImageQuery extends GenericQuery<ItemImage> {
        public ItemImageQuery hasItem(Item item);
    }
}
