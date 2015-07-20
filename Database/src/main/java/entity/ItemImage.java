package entity;

import javax.persistence.*;

/**
 * Created by Andrii on 20/07/2015.
 */
@Entity
@Table(name = "ITEM_IMAGE")
public class ItemImage {
    @Id
    @GeneratedValue
    protected int id;

    @Column(name = "IMAGE")
    @Lob
    protected byte[] image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    protected Item item;

    public ItemImage() {
    }

    public ItemImage(byte[] image, Item item) {
        this.image = image;
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
