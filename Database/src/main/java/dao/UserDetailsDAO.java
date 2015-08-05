package dao;

import entity.User;
import entity.UserDetails;
import org.hibernate.criterion.Criterion;

/**
 * Created by Andrii on 28/07/2015.
 */
public interface UserDetailsDAO extends GenericDAO<UserDetails> {
    public Criterion hasUser(User user);
}
