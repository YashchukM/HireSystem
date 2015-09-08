package manager.impl;

import dao.GenericDAO;
import dao.MessageDAO;
import entity.Message;
import entity.User;
import manager.MessageManager;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Andrii on 31/07/2015.
 */
@Service
public class MessageManagerImpl extends GenericManagerImpl<Message>
        implements MessageManager {

    @Autowired
    public MessageManagerImpl(MessageDAO messageDAO) {
        super(Message.class, messageDAO);
    }

    @Override
    public MessageQuery query() {
        return new MessageQueryImpl(dao);
    }

    public class MessageQueryImpl extends GenericQueryImpl<Message>
            implements MessageQuery {

        public MessageQueryImpl(GenericDAO<Message> dao) {
            super(dao);
            criteria.createAlias(criteria.getAlias() + ".fromUser", "fromUser", JoinType.INNER_JOIN);
            criteria.createAlias(criteria.getAlias() + ".toUser", "toUser", JoinType.INNER_JOIN);
        }

        @Override
        public MessageQuery hasSender(User sender) {
            addCriterion(((MessageDAO) dao).hasSender(sender));
            return this;
        }

        @Override
        public MessageQuery hasReceiver(User receiver) {
            addCriterion(((MessageDAO) dao).hasReceiver(receiver));
            return this;
        }

        @Override
        public MessageQuery hasDateBetween(Date lo, Date hi) {
            addCriterion(((MessageDAO) dao).hasDateBetween(lo, hi));
            return this;
        }
    }
}
