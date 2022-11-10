package app.user.model;

import app.person.model.Person;
import app.user.dtos.CreateUserDTO;

import javax.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "userTable")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(name="email", unique=true, nullable=false)
    private String email;
    @Column(name="password", unique=false, nullable=false)
    private String password;
    @OneToOne
    @JoinColumn(name = "personId")
    private Person person;

    public User() {
    }
    public User(CreateUserDTO userDTO,Person person) {
        this.person = new Person();
        this.userId = 0;
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.person = person;
    }

    public User(int userId, String email, String password, Person person) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(person, user.person);
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
