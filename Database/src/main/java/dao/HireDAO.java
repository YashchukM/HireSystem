package dao;

import entity.Hire;
import entity.Item;
import entity.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

import java.util.Date;

/**
 * Created by Andrii on 22/07/2015.
 */
public interface HireDAO extends GenericDAO<Hire> {
    public Criterion hasItem(Item item);
    public Criterion hasHirer(User hirer);
    public Criterion hasOwner(User owner);
    public Criterion hasStartDateBetween(Date lo, Date hi);
}