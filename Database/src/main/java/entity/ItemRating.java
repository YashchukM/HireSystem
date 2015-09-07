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
@Table(name = "RATING_ITEM")
public class ItemRating {
    @Id
    @GeneratedValue
    protected int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RATING_ID")
    @Cascade(CascadeType.ALL)
    protected Rating rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    protected Item item;

    public ItemRating() {
    }

    public ItemRating(Rating rating, Item item) {
        this.rating = rating;
        this.item = item;
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
