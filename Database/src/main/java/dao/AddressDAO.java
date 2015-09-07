package dao;

import entity.Address;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Junction;

import java.util.List;

/**
 * Created by Andrii on 21/07/2015.
 */
public interface AddressDAO extends GenericDAO<Address> {
    public Criterion hasCountryLike(String country);
    public Criterion hasRegionLike(String region);
    public Criterion hasCityLike(String city);
    public Criterion hasStreetLike(String street);
    public Criterion hasBuildingLike(String building);
    public Criterion hasApartmentLike(String apartment);
}
