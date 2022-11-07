package app.person.model;

import app.user.model.User;

import javax.persistence.*;

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

@Entity
public class Person extends User {
    @Column(name="name", unique=false, nullable=false)
    String name;
    @Column(name="surname", unique=false, nullable=false)
    private String surname;
    @Column(name="personType", unique=false, nullable=false)
    private PersonType personType;
    @OneToOne(mappedBy = "person",cascade = CascadeType.ALL)
    private Address address;
    @Column(name="uuid", unique=true, nullable=false)
    int uuid;
    @Column(name="phoneNumber", unique=false, nullable=false)
    int phoneNumber;
    @Column(name="school", unique=false, nullable=false)
    String school;
}
