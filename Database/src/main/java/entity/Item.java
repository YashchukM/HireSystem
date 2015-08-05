package entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by Andrii on 19/07/2015.
 */
@Entity
@Table(name = "ITEM")
public class Item {
    @Id
    @GeneratedValue
    protected int id;

    @Column(name = "NAME")
    protected String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    protected Category category;

    @Column(name = "PRICE")
    protected float price;

    @Column(name = "IS_HIRED")
    protected boolean isHired;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "item")
    @Cascade(CascadeType.ALL)
    protected ItemDetails itemDetails;

    @Column(name = "MAIN_IMAGE")
    protected byte[] mainImage;

    public Item() {
    }

    public Item(String name, Category category, float price,
                boolean isHired, ItemDetails itemDetails, byte[] mainImage) {

        this.name = name;
        this.category = category;
        this.price = price;
        this.isHired = isHired;
        this.itemDetails = itemDetails;
        this.mainImage = mainImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isHired() {
        return isHired;
    }

    public void setHired(boolean isHired) {
        this.isHired = isHired;
    }

    public ItemDetails getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(ItemDetails itemDetails) {
        this.itemDetails = itemDetails;
    }

    public byte[] getMainImage() {
        return mainImage;
    }

    public void setMainImage(byte[] mainImage) {
        this.mainImage = mainImage;
    }
}