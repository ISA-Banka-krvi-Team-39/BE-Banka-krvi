package app.shared.service;

import app.shared.model.Address;
import app.shared.repository.IAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AddressService implements IAddressService {

    @Autowired
    private IAddressRepository addressRepository;

    @Transactional
    public Address update(Address address) { return addressRepository.save(address); }

    public Address findOne(Address address){ return addressRepository.findOneByAddressId(address.getAddressId());}
}
