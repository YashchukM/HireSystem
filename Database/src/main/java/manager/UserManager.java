package manager;

import entity.User;

import java.util.Date;

/**
 * Created by Andrii on 31/07/2015.
 */
public interface UserManager extends GenericManager<User> {
    @Override
    public UserQuery query();

    public interface UserQuery extends GenericQuery<User> {
        public UserQuery hasLogin(String login);
        public UserQuery hasNameLike(String name);
        public UserQuery hasSurnameLike(String surname);
        public UserQuery hasBirthDateBetween(Date lo, Date hi);
        public UserQuery hasEmailLike(String email);
        public UserQuery hasPhoneLike(String phone);
    }
}
