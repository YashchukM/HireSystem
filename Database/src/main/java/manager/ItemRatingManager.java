package manager;

import entity.Item;
import entity.ItemRating;
import entity.User;

/**
 * Created by Andrii on 31/07/2015.
 */
public interface ItemRatingManager extends GenericManager<ItemRating> {
    @Override
    public ItemRatingQuery query();

    public interface ItemRatingQuery extends GenericQuery<ItemRating> {
        public ItemRatingQuery hasItem(Item item);
        public ItemRatingQuery hasAuthor(User author);
    }
}
