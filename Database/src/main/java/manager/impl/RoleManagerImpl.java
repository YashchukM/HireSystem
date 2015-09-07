package manager.impl;

import dao.GenericDAO;
import dao.RoleDAO;
import entity.Role;
import manager.RoleManager;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Andrii on 31/07/2015.
 */
@Service
public class RoleManagerImpl extends GenericManagerImpl<Role>
        implements RoleManager {

    @Autowired
    public RoleManagerImpl(RoleDAO roleDAO) {
        super(Role.class, roleDAO);
    }

    @Override
    public RoleQuery query() {
        return new RoleQueryImpl(dao);
    }

    public class RoleQueryImpl extends GenericQueryImpl<Role> implements RoleQuery {

        public RoleQueryImpl(GenericDAO<Role> dao) {
            super(dao);
            //criteria.createAlias(criteria.getAlias() + ".userDetails", "userDetails", JoinType.INNER_JOIN);
        }
    
        public RoleQuery hasName(String name) {
            addCriterion(((RoleDAO) dao).hasName(name));
            return this;
        }
    }
}
