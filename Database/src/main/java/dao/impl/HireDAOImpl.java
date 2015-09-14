package dao.impl;

import dao.HireDAO;
import entity.Hire;
import entity.Item;
import entity.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by Andrii on 22/07/2015.
 */
@Repository
public class HireDAOImpl extends GenericDAOImpl<Hire> implements HireDAO {
    @Override
    public Criterion hasItem(Item item) {
        return Restrictions.eq("item.id", item.getId());
    }

    @Override
    public Criterion hasHirer(User hirer) {
        return Restrictions.eq("hirer.id", hirer.getId());
    }

    @Override
    public Criterion hasOwner(User owner) {
        return Restrictions.eq("owner.id", owner.getId());
    }

    @Override
    public Criterion hasStartDateBetween(Date lo, Date hi) {
        return Restrictions.between("startDate", lo, hi);
    }

    @Override
    public Criterion hasStatus(int status) {
        return Restrictions.eq("status", status);
    }
}
