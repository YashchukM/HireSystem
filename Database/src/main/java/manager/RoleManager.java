package manager;

import entity.Role;

import java.util.Date;

/**
 * Created by Andrii on 31/07/2015.
 */
public interface RoleManager extends GenericManager<Role> {
    @Override
    public RoleQuery query();

    public interface RoleQuery extends GenericQuery<Role> {
        public RoleQuery hasName(String name);
    }
}
