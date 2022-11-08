package app.person.model;

import app.user.model.User;

import javax.persistence.*;
import java.util.Objects;

enum PersonGender {
    MALE,
    FEMALE,
    ALIEN,
}

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;
    @Column(name="name", unique=false, nullable=false)
    private String name;
    @Column(name="surname", unique=false, nullable=false)
    private String surname;
    @OneToOne(mappedBy = "person",cascade = CascadeType.ALL)
    private Address address;
    @Column(name="uuid", unique=true, nullable=false)
    private int uuid;
    @Column(name="phoneNumber", unique=false, nullable=false)
    private int phoneNumber;
    @Column(name="school", unique=false, nullable=false)
    private String school;
    @OneToOne(mappedBy = "person",cascade = CascadeType.ALL)
    private User user;
    
    public Person() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return personId == person.personId && uuid == person.uuid && phoneNumber == person.phoneNumber && Objects.equals(name, person.name) && Objects.equals(surname, person.surname) && Objects.equals(address, person.address) && Objects.equals(school, person.school) && Objects.equals(user, person.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, name, surname, address, uuid, phoneNumber, school, user);
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Address getAddress() {
        return address;
    }

    public int getUuid() {
        return uuid;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getSchool() {
        return school;
    }

    public User getUser() {
        return user;
    }
}
