package app.person.dtos;

import app.patient.model.Patient;
import app.person.model.PersonGender;
import app.person.model.PersonType;
import app.shared.model.Address;
import app.user.model.User;

public class GetPersonForProfileDTO {

    private String email;

    private String password;

    private String name;

    private PersonType personType;

    private PersonGender personGender;

    private String surname;

    private Address address;

    private String uuid;

    private String phoneNumber;

    private String school;

    private Integer points;

    public GetPersonForProfileDTO() {

    }

    public GetPersonForProfileDTO(User user, Patient patient) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getPerson().getName();
        this.surname = user.getPerson().getSurname();
        this.personType = user.getPerson().getPersonType();
        this.personGender = user.getPerson().getPersonGender();
        this.address = user.getPerson().getAddress();
        this.uuid = user.getPerson().getUuid();
        this.phoneNumber = user.getPerson().getPhoneNumber();
        this.school = user.getPerson().getSchool();
        this.points = patient.getPoints();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public PersonGender getPersonGender() {
        return personGender;
    }

    public void setPersonGender(PersonGender personGender) {
        this.personGender = personGender;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
