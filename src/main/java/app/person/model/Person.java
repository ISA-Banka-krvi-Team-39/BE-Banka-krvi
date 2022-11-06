package app.person.model;

import app.user.model.User;

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
    String name;
    String surname;
    PersonType personType;
    Address address;
    int uuid;
    int phoneNumber;
    String school;
}
