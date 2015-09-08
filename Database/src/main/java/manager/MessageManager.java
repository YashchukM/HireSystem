package manager;

import entity.Message;
import entity.User;

import java.util.Date;

/**
 * Created by Andrii on 31/07/2015.
 */
public interface MessageManager extends GenericManager<Message> {
    @Override
    public MessageQuery query();

    public interface MessageQuery extends GenericQuery<Message> {
        public MessageQuery hasSender(User sender);
        public MessageQuery hasReceiver(User receiver);
        public MessageQuery hasDateBetween(Date lo, Date hi);
    }
}
