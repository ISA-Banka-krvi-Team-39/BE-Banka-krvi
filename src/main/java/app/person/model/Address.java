package app.person.model;

import app.user.model.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

    @Column(name="country", unique=false, nullable=false)
    private String country;

    @Column(name="city", unique=false, nullable=false)
    private String city;

    @Column(name="streetName", unique=false, nullable=false)
    private String streetName;

    @Column(name="streetNumber", unique=false, nullable=false)
    private String streetNumber;

    @OneToOne(mappedBy = "address")
    private Person person;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return country.equals(address.country) && city.equals(address.city) && streetName.equals(address.streetName) && streetNumber.equals(address.streetNumber);
    }


    public Address(String country, String city, String streetName, String streetNumber) {
        this.country = country;
        this.city = city;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
    }

    public Address() {
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }
}
