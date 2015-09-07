package entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Andrii on 19/07/2015.
 */
@Entity
@Table(name = "RATING_REVIEW")
public class ReviewRating {
    @Id
    @GeneratedValue
    protected int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RATING_ID")
    @Cascade(CascadeType.ALL)
    protected Rating rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REVIEW_ID")
    protected Review review;

    public ReviewRating() {
    }

    public ReviewRating(Rating rating, Review review) {
        this.rating = rating;
        this.review = review;
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

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
