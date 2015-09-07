package dao;

import entity.Address;
import entity.Role;
import org.hibernate.criterion.Criterion;

import java.util.Date;

/**
 * Created by Andrii on 27/07/2015.
 */
public interface RoleDAO extends GenericDAO<Role> {
    public Criterion hasName(String name);
}
