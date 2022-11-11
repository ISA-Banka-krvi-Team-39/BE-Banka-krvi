package app.shared.repository;

import app.center.model.Center;
import app.shared.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<Address, Integer> {
    public Address findOneByAddressId(Integer addressId);
}
