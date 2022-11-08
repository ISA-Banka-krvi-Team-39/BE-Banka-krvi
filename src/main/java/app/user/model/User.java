package app.user.model;

import app.person.model.Address;
import app.person.model.Person;

import javax.persistence.*;
import javax.validation.constraints.Min;

import java.util.Objects;

import static javax.persistence.InheritanceType.JOINED;

enum PersonType {
    ADMIN,
    BLOODDONOR,
    MEDICALSERVICE,
}

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int userId;
    @Column(name="email", unique=true, nullable=false)
    private String email;
    @Column(name="password", unique=false, nullable=false)
    private String password;
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Person person;
    
    @Column(name="personType", unique=false, nullable=false)
    private PersonType personType;

    public User() {
    }

    public User(int userId, String email, String password, Person person, PersonType personType) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.person = person;
        this.personType = personType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(person, user.person) && personType == user.personType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, password, person, personType);
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Person getPerson() {
        return person;
    }
}
