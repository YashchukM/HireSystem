package dao.impl;

import dao.CategoryDAO;
import entity.Category;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Andrii on 22/07/2015.
 */
@Repository
public class CategoryDAOImpl extends GenericDAOImpl<Category> implements CategoryDAO {
    @Override
    public Criterion hasNameLike(String name) {
        return Restrictions.ilike("name", name, MatchMode.ANYWHERE);
    }
}
