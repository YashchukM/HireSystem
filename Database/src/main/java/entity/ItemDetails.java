package entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Andrii on 20/07/2015.
 */
@Entity
@Table(name = "ITEM_DETAILS")
public class ItemDetails {
    @GenericGenerator(name = "generator", strategy = "foreign",
    parameters = @Parameter(name = "property", value = "item"))
    @Id
    @GeneratedValue(generator = "generator")
    protected int id;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    //@Cascade(CascadeType.ALL)
    protected Item item;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OWNER_ID")
    protected User owner;

    @Column(name = "DESCRIPTION")
    @Lob
    // fixme : possible error because of @Lob annotation
    protected String description;

    @Column(name = "MIN_HIRE_TIME")
    protected float minHireTime;

    @Column(name = "MAX_HIRE_TIME")
    protected float maxHireTime;

    public ItemDetails() {
    }

    public ItemDetails(Item item, User owner, String description,
                       float minHireTime, float maxHireTime) {

        this.item = item;
        this.owner = owner;
        this.description = description;
        this.minHireTime = minHireTime;
        this.maxHireTime = maxHireTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getMinHireTime() {
        return minHireTime;
    }

    public void setMinHireTime(float minHireTime) {
        this.minHireTime = minHireTime;
    }

    public float getMaxHireTime() {
        return maxHireTime;
    }

    public void setMaxHireTime(float maxHireTime) {
        this.maxHireTime = maxHireTime;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
