package manager;

import entity.Address;

import java.util.List;

/**
 * Created by Andrii on 21/07/2015.
 */
public interface AddressManager extends GenericManager<Address> {
    @Override
    public AddressQuery query();

    public interface AddressQuery extends GenericQuery<Address> {
        public AddressQuery hasCountryLike(String country);
        public AddressQuery hasRegionLike(String region);
        public AddressQuery hasCityLike(String city);
        public AddressQuery hasStreetLike(String street);
        public AddressQuery hasBuildingLike(String building);
        public AddressQuery hasApartmentLike(String apartment);
    }
}
