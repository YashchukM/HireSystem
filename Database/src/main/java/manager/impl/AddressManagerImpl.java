package manager.impl;

import dao.AddressDAO;
import dao.GenericDAO;
import entity.Address;
import manager.AddressManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andrii on 30/07/2015.
 */
@Service
public class AddressManagerImpl extends GenericManagerImpl<Address>
        implements AddressManager {

    @Autowired
    public AddressManagerImpl(AddressDAO addressDAO) {
        super(Address.class, addressDAO);
    }

    @Override
    public AddressQuery query() {
        return new AddressQueryImpl(dao);
    }

    public class AddressQueryImpl extends GenericQueryImpl<Address>
            implements AddressQuery {

        public AddressQueryImpl(GenericDAO<Address> dao) {
            super(dao);
        }

        @Override
        public AddressQuery hasCountryLike(String country) {
            addCriterion(((AddressDAO) dao).hasCountryLike(country));
            return this;
        }

        @Override
        public AddressQuery hasRegionLike(String region) {
            addCriterion(((AddressDAO) dao).hasRegionLike(region));
            return this;
        }

        @Override
        public AddressQuery hasCityLike(String city) {
            addCriterion(((AddressDAO) dao).hasCityLike(city));
            return this;
        }

        @Override
        public AddressQuery hasStreetLike(String street) {
            addCriterion(((AddressDAO) dao).hasStreetLike(street));
            return this;
        }

        @Override
        public AddressQuery hasBuildingLike(String building) {
            addCriterion(((AddressDAO) dao).hasBuildingLike(building));
            return this;
        }

        @Override
        public AddressQuery hasApartmentLike(String apartment) {
            addCriterion(((AddressDAO) dao).hasApartmentLike(apartment));
            return this;
        }
    }
}
