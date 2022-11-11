package app.shared.model.service;

import app.shared.model.Address;

public interface IAddressService {

    public Address update(Address address);

    public Address findOne(Address address);
}
