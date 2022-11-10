package app.user.dtos;

import app.person.model.PersonGender;
import app.person.model.PersonType;
import app.shared.model.Address;

import javax.validation.constraints.Email;

public class CreateUserDTO {
    private String email;
    private String name;
    private String surname;
    private String password;
    private PersonType personType;
    private PersonGender personGender;
    private Address address;
    private String uuid;
    private String phoneNumber;
    private String school;

    public CreateUserDTO() {
    }

    public CreateUserDTO(String email, String name, String surname, String password, PersonType personType, PersonGender personGender, Address address, String uuid, String phoneNumber, String school) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.personType = personType;
        this.personGender = personGender;
        this.address = address;
        this.uuid = uuid;
        this.phoneNumber = phoneNumber;
        this.school = school;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public void setPersonGender(PersonGender personGender) {
        this.personGender = personGender;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public PersonGender getPersonGender() {
        return personGender;
    }

    public Address getAddress() {
        return address;
    }

    public String getUuid() {
        return uuid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSchool() {
        return school;
    }
}
