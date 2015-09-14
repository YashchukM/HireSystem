
package org.league.hire.bo;

import java.util.Vector;
import org.league.hire.beans.ChatUser;

/**
 * IBO interface the needed method to implement by the Business objects

 */
public interface IBO {
    public Vector<ChatUser> loadUsers();
}
