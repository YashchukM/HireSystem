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
    protected ItemDetails itemDetails;

    public ItemImage() {
    }

    public ItemImage(byte[] image, ItemDetails itemDetails) {
        this.image = image;
        this.itemDetails = itemDetails;
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

    public ItemDetails getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(ItemDetails itemDetails) {
        this.itemDetails= itemDetails;
    }
}
