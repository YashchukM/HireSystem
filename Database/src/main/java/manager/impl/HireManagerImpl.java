package manager.impl;

import dao.GenericDAO;
import dao.HireDAO;
import entity.Hire;
import entity.Item;
import entity.User;
import manager.HireManager;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Andrii on 31/07/2015.
 */
@Service
public class HireManagerImpl extends GenericManagerImpl<Hire>
        implements HireManager {

    @Autowired
    public HireManagerImpl(HireDAO hireDAO) {
        super(Hire.class, hireDAO);
    }

    @Override
    public HireQuery query() {
        return new HireQueryImpl(dao);
    }

    public class HireQueryImpl extends GenericQueryImpl<Hire>
            implements HireQuery {

        public HireQueryImpl(GenericDAO<Hire> dao) {
            super(dao);
            criteria.createAlias(criteria.getAlias() + ".item", "item", JoinType.INNER_JOIN);
            criteria.createAlias(criteria.getAlias() + ".hirer", "hirer", JoinType.INNER_JOIN);
            criteria.createAlias(criteria.getAlias() + ".owner", "owner", JoinType.INNER_JOIN);
        }

        @Override
        public HireQuery hasItem(Item item) {
            addCriterion(((HireDAO) dao).hasItem(item));
            return this;
        }

        @Override
        public HireQuery hasHirer(User hirer) {
            addCriterion(((HireDAO) dao).hasHirer(hirer));
            return this;
        }

        @Override
        public HireQuery hasOwner(User owner) {
            addCriterion(((HireDAO) dao).hasOwner(owner));
            return this;
        }

        @Override
        public HireQuery hasDateBetween(Date lo, Date hi) {
            addCriterion(((HireDAO) dao).hasStartDateBetween(lo, hi));
            return this;
        }
    }
}
