package entity;

import javax.persistence.*;

/**
 * Created by Andrii on 19/07/2015.
 */
@Entity
@Table(name = "ADDRESS")
public class Address {
    @Id
    @GeneratedValue
    protected int id;

    @Column(name = "COUNTRY")
    protected String country;

    @Column(name = "REGION")
    protected String region;

    @Column(name = "CITY")
    protected String city;

    @Column(name = "STREET")
    protected String street;

    @Column(name = "BUILDING")
    protected String building;

    @Column(name = "APARTMENT")
    protected String apartment;

    public Address() {
    }

    public Address(String country, String region, String city,
                   String street, String building, String apartment) {
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.building = building;
        this.apartment = apartment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }
}
