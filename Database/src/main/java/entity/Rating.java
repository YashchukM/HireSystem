package entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Andrii on 20/07/2015.
 */
@Entity
@Table(name = "RATING")
public class Rating {
    @Id
    @GeneratedValue
    protected int id;

    @Column(name = "RATING_DATE")
    protected Date date;

    @Column(name = "VALUE")
    protected int value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AUTHOR_USER_ID")
    protected User author;

    public Rating() {
    }

    public Rating(Date date, int value, User author) {
        this.date = date;
        this.value = value;
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
