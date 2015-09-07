package dao.impl;

import dao.AddressDAO;
import entity.Address;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.*;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Andrii on 21/07/2015.
 */
@Repository
public class AddressDAOImpl extends GenericDAOImpl<Address> implements AddressDAO {
    @Override
    public Criterion hasCountryLike(String country) {
        return Restrictions.ilike("country", country, MatchMode.ANYWHERE);
    }

    @Override
    public Criterion hasRegionLike(String region) {
        return Restrictions.ilike("region", region, MatchMode.ANYWHERE);
    }

    @Override
    public Criterion hasCityLike(String city) {
        return Restrictions.ilike("city", city, MatchMode.ANYWHERE);
    }

    @Override
    public Criterion hasStreetLike(String street) {
        return Restrictions.ilike("street", street, MatchMode.ANYWHERE);
    }

    @Override
    public Criterion hasBuildingLike(String building) {
        return Restrictions.ilike("building", building, MatchMode.ANYWHERE);
    }

    @Override
    public Criterion hasApartmentLike(String apartment) {
        return Restrictions.ilike("apartment", apartment, MatchMode.ANYWHERE);
    }
}
