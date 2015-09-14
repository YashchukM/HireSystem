
package org.league.hire.beans;

import java.io.Serializable;



public class ChatMessage implements Serializable {
    public static int NEW_MESSAGE_TYPE=0;
    public static int RETURN_MESSAGE_TYPE=1;
    private String message;
    private int from_user;
    private int to_user;
    private String from_user_name;
    private String messageTime;
    private int messageType=0;
    public String toXML(){
        return "<chat_message><from_user>"+from_user+"</from_user><from_user_name>"+from_user_name+"</from_user_name><message>"+message+"</message><message_type>"+messageType+"</message_type></chat_message>";
    }
    public String toMultipleValues(){
        return from_user+"ø"+from_user_name+"ø"+message+"ø"+messageType+"ø";
    }
    public void returnMessage(){
        int temp=this.from_user;
        this.from_user=this.to_user;
        this.to_user=temp;
        this.messageType=1;
    }
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
        //prevent html tags
        this.message=this.message.replaceAll("<", "&lt;");
        //prevent special split charachter
        this.message=this.message.replaceAll("ø", "");
    }

    /**
     * @return the from_user
     */
    public int getFrom_user() {
        return from_user;
    }

    /**
     * @param from_user the from_user to set
     */
    public void setFrom_user(int from_user) {
        this.from_user = from_user;
    }

    /**
     * @return the to_user
     */
    public int getTo_user() {
        return to_user;
    }

    /**
     * @param to_user the to_user to set
     */
    public void setTo_user(int to_user) {
        this.to_user = to_user;
    }

    /**
     * @return the from_user_name
     */
    public String getFrom_user_name() {
        return from_user_name;
    }

    /**
     * @param from_user_name the from_user_name to set
     */
    public void setFrom_user_name(String from_user_name) {
        this.from_user_name = from_user_name;
    }

    /**
     * @return the messageTime
     */
    public String getMessageTime() {
        return messageTime;
    }

    /**
     * @param messageTime the messageTime to set
     */
    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    /**
     * @return the messageType
     */
    public int getMessageType() {
        return messageType;
    }

    /**
     * @param messageType the messageType to set
     */
    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }
    
}
