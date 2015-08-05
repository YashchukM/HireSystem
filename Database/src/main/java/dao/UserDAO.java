package dao;

import entity.Address;
import entity.User;
import org.hibernate.criterion.Criterion;

import java.util.Date;

/**
 * Created by Andrii on 27/07/2015.
 */
public interface UserDAO extends GenericDAO<User> {
    public Criterion hasLogin(String login);
    public Criterion hasNameLike(String name);
    public Criterion hasSurnameLike(String surname);
    public Criterion hasBirthDateBetween(Date lo, Date hi);
    public Criterion hasEmailLike(String email);
    public Criterion hasPhoneLike(String phone);
}
