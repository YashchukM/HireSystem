package dao.impl;

import dao.RoleDAO;
import entity.Role;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Andrii on 28/07/2015.
 */
@Repository
public class RoleDAOImpl extends GenericDAOImpl<Role> implements RoleDAO {
    public Criterion hasName(String name) {
        return Restrictions.eq("name", name);
    }
}
