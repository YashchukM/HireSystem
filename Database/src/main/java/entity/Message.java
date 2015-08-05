package entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Andrii on 19/07/2015.
 */
@Entity
@Table(name = "MESSAGE")
public class Message {
    @Id
    @GeneratedValue
    protected int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FROM_USER_ID")
    protected User fromUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TO_USER_ID")
    protected User toUser;

    @Column(name = "CONTENT")
    @Lob
    protected String content;

    @Column(name = "SENT_DATE")
    protected Date sentDate;

    public Message() {
    }

    public Message(User fromUser, User toUser, String content, Date sentDate) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.content = content;
        this.sentDate = sentDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }
}
