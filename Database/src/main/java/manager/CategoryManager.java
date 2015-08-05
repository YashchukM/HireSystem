package manager;

import entity.Category;

/**
 * Created by Andrii on 31/07/2015.
 */
public interface CategoryManager extends GenericManager<Category> {
    @Override
    public CategoryQuery query();

    public interface CategoryQuery extends GenericQuery<Category> {
        public CategoryQuery hasNameLike(String name);
    }
}
