package app.user.dto;

import app.patient.model.BloodType;
import app.person.model.PersonGender;
import app.person.model.PersonType;
import app.shared.model.Address;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class CreateUserDTO {
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;
    @NotEmpty(message = "Name is mandatory")
    private String name;
    @NotEmpty(message = "Surname is mandatory")
    private String surname;
    @NotEmpty(message = "Password is mandatory")
    private String password;
    @NotNull(message = "You must choose person type")
    private PersonType personType;
    @NotNull(message = "You must choose your gender")
    private PersonGender personGender;
    @NotNull(message = "You must set address")
    private Address address;
    @Pattern(regexp="^[0-9]{5}",message="length must be 5")
    private String uid;
    @NotEmpty(message = "Phone number is mandatory")
    private String phoneNumber;
    @NotEmpty(message = "school is mandatory")
    private String school;
    @NotNull(message = "You must choose your blood type")
    private BloodType bloodType;

    public CreateUserDTO() {
    }

    public CreateUserDTO(String email, String name, String surname, String password, PersonType personType, 
                         PersonGender personGender, Address address, String uid, String phoneNumber, String school,
                         BloodType bloodType) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.personType = personType;
        this.personGender = personGender;
        this.address = address;
        this.uid = uid;
        this.phoneNumber = phoneNumber;
        this.school = school;
        this.bloodType = bloodType;
    }


    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
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

    public void setUid(String uuid) {
        this.uid = uuid;
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

    public String getUid() {
        return uid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSchool() {
        return school;
    }
}
