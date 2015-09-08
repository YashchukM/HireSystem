package dao;

import entity.Category;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * Created by Andrii on 22/07/2015.
 */
public interface CategoryDAO extends GenericDAO<Category> {
    public Criterion hasNameLike(String name);
}
