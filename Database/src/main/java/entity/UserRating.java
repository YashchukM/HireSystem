package entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Andrii on 20/07/2015.
 */
@Entity
@Table(name = "RATING_USER")
public class UserRating {
    @Id
    @GeneratedValue
    protected int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RATING_ID")
    @Cascade(CascadeType.ALL)
    protected Rating rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    protected User user;

    public UserRating() {
    }

    public UserRating(Rating rating, User user) {
        this.rating = rating;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
