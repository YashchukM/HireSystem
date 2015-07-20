package entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Andrii on 19/07/2015.
 */
@Entity
@Table(name = "REVIEW")
public class Review {
    @Id
    @GeneratedValue
    protected int id;

    @Column(name = "REVIEW_DATE")
    protected Date date;

    @Column(name = "REVIEW_TEXT")
    @Lob
    // fixme : possible error because of @Lob annotation
    protected String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AUTHOR_USER_ID")
    protected User author;

    public Review() {
    }

    public Review(Date date, String text, User author) {
        this.date = date;
        this.text = text;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
