package app.user.model;

import app.person.model.Person;

import javax.persistence.*;
import javax.validation.constraints.Min;

import static javax.persistence.InheritanceType.JOINED;

@Entity
@Inheritance(strategy=JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int userId;
    @Column(name="owner", unique=true, nullable=false)
    String email;
    @Column(name="owner", unique=false, nullable=false)
    String password;

}
