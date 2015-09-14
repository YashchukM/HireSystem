package manager;

import entity.Hire;
import entity.Item;
import entity.User;
import manager.impl.GenericManagerImpl;

import java.util.Date;

/**
 * Created by Andrii on 31/07/2015.
 */
public interface HireManager extends GenericManager<Hire> {
    @Override
    public HireQuery query();

    public interface HireQuery extends GenericQuery<Hire> {
        public HireQuery hasItem(Item item);
        public HireQuery hasHirer(User hirer);
        public HireQuery hasOwner(User owner);
        public HireQuery hasDateBetween(Date lo, Date hi);
        public HireQuery hasStatus(int status);
    }
}
