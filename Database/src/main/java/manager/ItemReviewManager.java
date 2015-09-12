package manager;

import entity.Item;
import entity.ItemReview;
import entity.User;

/**
 * Created by Andrii on 31/07/2015.
 */
public interface ItemReviewManager extends GenericManager<ItemReview> {
    @Override
    public ItemReviewQuery query();

    public interface ItemReviewQuery extends GenericQuery<ItemReview> {
        public ItemReviewQuery hasItem(Item item);
        public ItemReviewQuery hasAuthor(User author);
        public ItemReviewQuery hasReviewId(int id);
    }
}
