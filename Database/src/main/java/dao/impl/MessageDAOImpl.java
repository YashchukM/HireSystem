package dao.impl;

import dao.MessageDAO;
import entity.Message;
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
public class MessageDAOImpl extends GenericDAOImpl<Message> implements MessageDAO {
    @Override
    public Criterion hasSender(User sender) {
        return Restrictions.eq("fromUser.id", sender.getId());
    }

    @Override
    public Criterion hasReceiver(User receiver) {
        return Restrictions.eq("toUser.id", receiver.getId());
    }

    @Override
    public Criterion hasDateBetween(Date lo, Date hi) {
        return Restrictions.between("sentDate", lo, hi);
    }
}
