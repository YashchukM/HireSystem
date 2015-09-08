package manager;

import entity.User;
import entity.UserDetails;

/**
 * Created by Andrii on 31/07/2015.
 */
public interface UserDetailsManager extends GenericManager<UserDetails> {
    @Override
    public UserDetailsQuery query();

    public interface UserDetailsQuery extends GenericQuery<UserDetails> {
        public UserDetailsQuery hasUser(User user);
    }
}
