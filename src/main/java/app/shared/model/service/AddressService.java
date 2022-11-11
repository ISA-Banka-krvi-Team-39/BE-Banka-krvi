package app.shared.model.service;

import app.person.model.Person;
import app.person.repository.IPersonRepository;
import app.shared.model.Address;
import app.shared.model.repository.IAddressRepository;
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
