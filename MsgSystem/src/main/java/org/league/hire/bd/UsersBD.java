package org.league.hire.bd;

import java.util.Vector;
import org.league.hire.beans.ChatUser;
import org.league.hire.bo.FileBO;
import org.league.hire.bo.IBO;


public class UsersBD implements IBO {
    public static final String FILE_TYPE="1";
    public static final String DB_TYPE="2";
    private IBO businessObject;
    public UsersBD(String type) throws Exception{
        if(type==null || type.equals(FILE_TYPE)){
            businessObject=new FileBO();
        }else if(type.equals(DB_TYPE)){
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public Vector<ChatUser> loadUsers() {
        return businessObject.loadUsers();
    }
}
