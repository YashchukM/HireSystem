package manager;

import entity.Category;
import entity.Item;
import entity.User;

/**
 * Created by Andrii on 31/07/2015.
 */
public interface ItemManager extends GenericManager<Item> {
    @Override
    public ItemQuery query();

    public interface ItemQuery extends GenericQuery<Item> {
        public ItemQuery hasNameLike(String name);
        public ItemQuery hasCategory(Category category);
        public ItemQuery hasPriceBetween(float lo, float hi);
        public ItemQuery hasHired(boolean isHired);
        public ItemQuery hasOwner(User owner);
    }
}
