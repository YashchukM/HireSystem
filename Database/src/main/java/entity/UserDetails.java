package entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Andrii on 20/07/2015.
 */
@Entity
@Table(name = "USER_DETAILS")
public class UserDetails {
    @GenericGenerator(name = "generator", strategy = "foreign",
    parameters = @Parameter(name = "property", value = "user"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "USER_ID")
    protected int id;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @Cascade(CascadeType.ALL)
    protected User user;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "SURNAME")
    protected String surname;

    @Column(name = "BIRTH_DATE")
    protected Date birthDate;

    @Column(name = "EMAIL")
    protected String email;

    @Column(name = "PHONE")
    protected String phone;

    @Column(name = "IMAGE")
    @Lob
    protected byte[] image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADDRESS_ID")
    protected Address address;

    public UserDetails() {
    }

    public UserDetails(User user, String name, String surname, Date birthDate,
                       String email, String phone, byte[] image, Address address) {

        this.user = user;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
        this.image = image;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
