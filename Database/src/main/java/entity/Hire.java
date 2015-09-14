package entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Andrii on 19/07/2015.
 */
@Entity
@Table(name = "HIRE")
public class Hire {
    @Id
    @GeneratedValue
    protected int id;

    @ManyToOne(fetch = FetchType.EAGER)
    protected Item item;

    @ManyToOne(fetch = FetchType.EAGER)
    protected User owner;

    @ManyToOne(fetch = FetchType.EAGER)
    protected User hirer;

    @Column(name = "START_DATE")
    protected Date startDate;

    @Column(name = "END_DATE")
    protected Date endDate;

    @Column(name = "STATUS")
    protected int status;

    public Hire() {
    }

    public Hire(Item item, User owner, User hirer, Date startDate, Date endDate) {
        this.item = item;
        this.owner = owner;
        this.hirer = hirer;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Hire(Item item, User owner, User hirer, Date startDate, Date endDate, int status) {
        this.item = item;
        this.owner = owner;
        this.hirer = hirer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getHirer() {
        return hirer;
    }

    public void setHirer(User hirer) {
        this.hirer = hirer;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
