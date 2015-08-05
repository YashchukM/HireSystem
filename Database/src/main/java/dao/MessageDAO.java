package dao;

import entity.Message;
import entity.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

import java.util.Date;

/**
 * Created by Andrii on 22/07/2015.
 */
public interface MessageDAO extends GenericDAO<Message> {
    public Criterion hasSender(User sender);
    public Criterion hasReceiver(User receiver);
    public Criterion hasDateBetween(Date lo, Date hi);
}
