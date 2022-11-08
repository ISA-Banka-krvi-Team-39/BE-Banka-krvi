package app.user.model;

import app.person.model.Person;

import javax.persistence.*;

import java.util.Objects;

enum PersonType {
    ADMIN,
    BLOODDONOR,
    MEDICALSERVICE,
}

@Entity
@Table(name = "userTable")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    @Column(name="email", unique=true, nullable=false)
    private String email;
    @Column(name="password", unique=false, nullable=false)
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "personId")
    private Person person;
    
    @Column(name="personType", unique=false, nullable=false)
    private PersonType personType;

    public User() {
    }

    public User(int userId, String email, String password, Person person, PersonType personType) {
        this.user_id = userId;
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
        return user_id == user.user_id && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(person, user.person) && personType == user.personType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, email, password, person, personType);
    }

    public void setUser_id(int userId) {
        this.user_id = userId;
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

    public int getUser_id() {
        return user_id;
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
