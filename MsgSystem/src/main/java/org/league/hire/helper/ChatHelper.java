
package org.league.hire.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.league.hire.bd.UsersBD;
import org.league.hire.beans.ChatMessage;
import org.league.hire.beans.ChatUser;
import org.league.hire.config.ChatConfig;

/**
 * ChatHelper Class that handle all needed communication between chat parties and keep track of messages and online users
 */
public class ChatHelper implements Runnable{
    private static ChatHelper chatHelper=new ChatHelper();
    private static Hashtable<Integer,Date> onlineUsers=new Hashtable<Integer, Date>();
    private static Hashtable<Integer,Queue<ChatMessage>> chatMessages=new Hashtable<Integer,Queue<ChatMessage>>();
    private Thread cleanThread;
    private static UsersBD userBd=null;
    private static Vector<ChatUser> usersList;
    static{
        try{
            userBd=new UsersBD(UsersBD.FILE_TYPE);
            usersList=userBd.loadUsers();
        }catch(Throwable t){
            System.err.println("Can't get chat users!");
            t.printStackTrace();
        }
    }
    private ChatHelper(){
        cleanThread=new Thread(this);
        cleanThread.start();
    }
    public static ChatHelper getChatHelper(){
        return chatHelper;
    }
    public void run() {
        try {
            //wait 10 minutes before start removing users
            cleanThread.sleep(10 * 60 * 1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        while(true){
            try{
                if(onlineUsers==null){
                    onlineUsers=new Hashtable<Integer, Date>();
                }
                if(onlineUsers.size()>0){
                    //do clean logic here
                    for(int i=0;i<onlineUsers.size();i++){
                        Enumeration<Integer> keys = onlineUsers.keys() ;
                        while(keys.hasMoreElements()) {
                            Integer current=keys.nextElement();
                            if(onlineUsers.get(current).getTime()+ChatConfig.expireLoginAfter<new Date().getTime()){
                                logOffUser(current);
                            }
                        }
                    }
                }
                //wait 3 minutes
                cleanThread.sleep(3*60*1000);
            }catch(Throwable t){
                t.printStackTrace();
            }
        }
    }
    public void logOffUser(int userId){
        //remove this user from online users table
        onlineUsers.remove(userId);
        for(int i=0;i<usersList.size();i++){
            if(usersList.get(i).getId()==userId){
                usersList.get(i).setStatus(ChatUser.OFFLINE);
                break;
            }
        }
        //remove the user messages from user queue and return to the sender(s)
        returnMessagesOfUser(userId);
    }
    /**
     * Login the user which means add the user to login table with timestamp
     * or update the current timestamp of the user in the table
     * @param user_id
     */
    public void loginUser(int user_id){
        //System.out.println("Online user="+user_id);
        //always put a new value for this user
        onlineUsers.put(user_id, new Date());
        for(int i=0;i<usersList.size();i++){
            if(usersList.get(i).getId()==user_id){
                usersList.get(i).setStatus(ChatUser.ONLINE);
                break;
            }
        }
    }
    /**
     * Check if user by userId is online or not online
     * @param userId
     * @return boolean online or not
     */
    public boolean isUserOnline(int userId){
        if(onlineUsers.get(userId)!=null){
            return true;
        }else{
            return false;
        }
    }
    /**
     * get User status either ONLINE or OFFLINE
     * @param userId
     * @return int represents user status
     */
    public int getUserStatus(int userId){
        if(isUserOnline(userId)) return ChatUser.ONLINE;
        else return ChatUser.OFFLINE;
    }
    /**
     * A message is recieved and should be put in the to_user queue if user is online
     * otherwise return the message to the sender.
     * @param chatMessage
     */
    public void recievedMessage(ChatMessage chatMessage){
        if(onlineUsers.get(chatMessage.getTo_user())!=null){
            //user online
            if(chatMessages.get(chatMessage.getTo_user())!=null){
                //queue is already exist
                chatMessages.get(chatMessage.getTo_user()).add(chatMessage);
            }else{
                Queue<ChatMessage> newQueue=new ConcurrentLinkedQueue<ChatMessage>();
                newQueue.add(chatMessage);
                chatMessages.put(chatMessage.getTo_user(), newQueue);
            }
            //System.out.println("Message Recieved!");
        }else{
            //user is not online, return the message
            chatMessage.returnMessage();
            if(onlineUsers.get(chatMessage.getTo_user())!=null){
                //user online
                if(chatMessages.get(chatMessage.getTo_user())!=null){
                    //queue is already exist
                    chatMessages.get(chatMessage.getTo_user()).add(chatMessage);
                }else{
                    Queue<ChatMessage> newQueue=new ConcurrentLinkedQueue<ChatMessage>();
                    newQueue.add(chatMessage);
                    chatMessages.put(chatMessage.getTo_user(), newQueue);
                }
                //System.out.println("Message Returned!");
            }
            //no else as the sender is sure online as
            //we called login the user at 1st line of this method
        }        
    }
    /**
     * This method loop over user queue to return user messages to the sender(s)
     * as the user is no longer online
     * @param current
     */
    private void returnMessagesOfUser(Integer current) {
        //System.out.println("Offline messages to user:"+current);
        //if there is a queue for this user , loop and return its messages
        Queue messages=chatMessages.get(current);
        //queue is already exist
        if(chatMessages.get(current)!=null){
            //if message >0
            if(messages.size()>0){
                //loop over the queue
                while(messages.size()>0){
                    //get the oldest message
                    ChatMessage message=(ChatMessage)messages.poll();
                    //return the message
                    message.returnMessage();
                    //resend it to the sender as returned message if only it is online
                    if(onlineUsers.get(message.getTo_user())!=null) {
                        recievedMessage(message);
                    }
                }
            }
            //remove the queue from the hashtable
            chatMessages.remove(current);
        }
    }
    /**
     * This method return the top message from the user queue according to the count variable
     * @param user_id
     * @param count
     * @return ChatMessage[]
     */
    public ChatMessage[] getUserMessages(int user_id,int count) {
        //if there is a queue for this user
        Queue messages=chatMessages.get(user_id);
        //queue is already exist
        if(chatMessages.get(user_id)!=null){
            ArrayList<ChatMessage> messagesList=new ArrayList<ChatMessage>();
            //if message >0
            if(messages.size()>0){
                //loop over the queue
                while(count>0){
                    //get the oldest message
                    ChatMessage message=(ChatMessage)messages.poll();
                    if(message==null){
                        break;
                    }
                    //put it in the queue
                    messagesList.add(message);
                    //decrease retrieved count
                    count--;
                }
            }
            return messagesList.toArray(new ChatMessage[0]);
        }
        return null;
    }
    public ChatUser authenticateUser(String username,String password){
        for(int i=0;i<usersList.size();i++){
            if(usersList.get(i).getLogin().equals(username) && usersList.get(i).getPassword().equals(password)){
                   return usersList.get(i);
            }
        }
        return null;
    }
    public ChatUser[] getContactList(int id){
        Vector<ChatUser> contactList=new Vector<ChatUser>();
        for(int i=0;i<usersList.size();i++){
            if(usersList.get(i).getId()!=id){
                   contactList.add(usersList.get(i));
            }
        }
        return contactList.toArray(new ChatUser[0]);
    }
}
