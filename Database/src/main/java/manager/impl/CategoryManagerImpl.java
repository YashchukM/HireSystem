package manager.impl;

import dao.CategoryDAO;
import dao.GenericDAO;
import entity.Category;
import manager.CategoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andrii on 31/07/2015.
 */
@Service
public class CategoryManagerImpl extends GenericManagerImpl<Category>
        implements CategoryManager{

    @Autowired
    public CategoryManagerImpl(CategoryDAO categoryDAO) {
        super(Category.class, categoryDAO);
    }

    @Override
    public CategoryQuery query() {
        return new CategoryQueryImpl(dao);
    }

    public class CategoryQueryImpl extends GenericQueryImpl<Category>
            implements CategoryQuery {


        public CategoryQueryImpl(GenericDAO<Category> dao) {
            super(dao);
        }

        @Override
        public CategoryQuery hasNameLike(String name) {
            addCriterion(((CategoryDAO) dao).hasNameLike(name));
            return this;
        }
    }
}
