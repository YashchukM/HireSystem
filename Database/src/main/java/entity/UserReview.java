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
@Table(name = "REVIEW_USER")
public class UserReview {
    @Id
    @GeneratedValue
    protected int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "REVIEW_ID")
    @Cascade(CascadeType.ALL)
    protected Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    protected User user;

    public UserReview() {
    }

    public UserReview(Review review, User user) {
        this.review = review;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
