package app.systemadmin.model;

import app.person.model.Person;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class SystemAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;
    @OneToOne
    @JoinColumn(name = "personId")
    private Person person;
    @Column(name="wasLoggedIn")
    private Boolean wasLoggedIn;

    public SystemAdmin() {}

    public SystemAdmin(Person person, Boolean wasLoggedIn) {
        this.person = person;
        this.wasLoggedIn = wasLoggedIn;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Boolean getWasLoggedIn() {
        return wasLoggedIn;
    }

    public void setWasLoggedIn(Boolean wasLoggedIn) {
        this.wasLoggedIn = wasLoggedIn;
    }

    public Integer getAdminId() {
        return adminId;
    }


}
