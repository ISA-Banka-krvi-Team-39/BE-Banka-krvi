package app.person.model;

import app.user.model.User;

import javax.persistence.Column;

enum PersonType {
    ADMIN,
    BLOODDONOR,
    MEDICALSERVICE,
}
enum PersonGender {
    MALE,
    FEMALE,
    ALIEN,
}

public class Person extends User {
    
    @Column(name="name", unique=false, nullable=false)
    String name;
    @Column(name="surname", unique=false, nullable=false)
    String surname;
    @Column(name="personType", unique=false, nullable=false)
    PersonType personType;
    @Column(name="owner", unique=false, nullable=false)
    Address address;
    int uuid;
    int phoneNumber;
    String school;
}
