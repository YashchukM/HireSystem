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
@Table(name = "REVIEW_ITEM")
public class ItemReview {
    @Id
    @GeneratedValue
    protected int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "REVIEW_ID")
    @Cascade(CascadeType.ALL)
    protected Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    protected Item item;

    public ItemReview() {
    }

    public ItemReview(Review review, Item item) {
        this.review = review;
        this.item = item;
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
