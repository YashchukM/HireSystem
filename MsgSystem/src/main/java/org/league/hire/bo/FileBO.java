
package org.league.hire.bo;

import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import org.league.hire.beans.ChatUser;
import org.league.hire.utils.XMLParser;
import entity.User;
import entity.UserDetails;
import entity.Role;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * FileBO Class is a business object that load from XML file the chat users , xml file is users.xml
 */
public class FileBO implements IBO {
    
    @Autowired
    private UserManager userManager;

    XMLParser parser1;
    Properties config;
    private static final String SERVER_DATA_FILE_NAME = "users.xml";

    public FileBO() throws Exception {
        loadXMLFile();
    }

    private void loadXMLFile() throws Exception {
        parser1 = new XMLParser(SERVER_DATA_FILE_NAME,false);
    }
    public Vector<ChatUser> loadUsers() {
        //System.out.println("Loading All Users");
        Vector<ChatUser> result = new Vector<ChatUser>(0);        
        //load groups
        List<User> temp = userManager.query().findInitialized(0, 1000);
        //System.out.println("All users size=" + tempVector.size());
        for (int i = 0; i < temp.size(); i++) {
            ChatUser chatUser = new ChatUser();          
            chatUser.setId(temp.get(i).getId());
            chatUser.setName(temp.get(i).getUserDetails().getName());
            chatUser.setLogin(temp.get(i).getLogin());
            chatUser.setEmail(temp.get(i).getUserDetails().getEmail());
            chatUser.setPassword(temp.get(i).getPassword());
            chatUser.setType(1);
            chatUser.setStatus(ChatUser.OFFLINE);
            result.add(chatUser);
        }
        System.gc();
        //System.out.println("Finish Loading All Users");
        return result;
    }
}
