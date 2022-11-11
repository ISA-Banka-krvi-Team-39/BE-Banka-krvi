package app.person.model;

import app.center.model.Center;
import app.center.model.Term;
import app.shared.model.Address;
import app.user.dtos.CreateUserDTO;
import app.user.dtos.UpdateUserDTO;
import app.user.model.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer personId;
    @Column(name="name", unique=false, nullable=false)
    private String name;

    @Column(name="personType", unique=false, nullable=false)
    private PersonType personType;
    
    @Column(name="personGender", unique=false, nullable=false)
    private PersonGender personGender;
    @Column(name="surname", unique=false, nullable=false)
    private String surname;
    
    @ManyToMany(mappedBy = "bloodDonors",fetch = FetchType.LAZY)
    private Set<Term> donatingTerms = new HashSet<Term>();

    @ManyToMany(mappedBy = "medicalStaff",fetch = FetchType.LAZY)
    private Set<Term> workingTerms = new HashSet<Term>();
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private Address address;

    @Column(name="uuid", unique=true, nullable=false)
    private String uuid;
    @Column(name="phoneNumber", unique=false, nullable=false)
    private String phoneNumber;
    @Column(name="school", unique=false, nullable=false)
    private String school;
    
    public Person() {
    }
    public Person(CreateUserDTO userDTO) {
        this.name = userDTO.getName();
        this.personType = userDTO.getPersonType();
        this.personGender = userDTO.getPersonGender();
        this.surname = userDTO.getSurname();
        this.address = userDTO.getAddress();
        this.uuid = userDTO.getUuid();
        this.phoneNumber = userDTO.getPhoneNumber();
        this.school = userDTO.getSchool();
    }

    public Person(Integer personId, String name, PersonType personType, PersonGender personGender, String surname, Set<Term> donatingTerms, Set<Term> workingTerms, Address address, String uuid, String phoneNumber, String school) {
        this.personId = personId;
        this.name = name;
        this.personType = personType;
        this.personGender = personGender;
        this.surname = surname;
        this.donatingTerms = donatingTerms;
        this.workingTerms = workingTerms;
        this.address = address;
        this.uuid = uuid;
        this.phoneNumber = phoneNumber;
        this.school = school;
    }

    public void updatePerson(UpdateUserDTO updateUserDTO){
        this.name = updateUserDTO.getName();
        this.surname = updateUserDTO.getSurname();
        this.personGender = updateUserDTO.getPersonGender();
        this.address = updateUserDTO.getAddress();
        this.uuid = updateUserDTO.getUuid();
        this.phoneNumber = updateUserDTO.getPhoneNumber();
        this.school = updateUserDTO.getSchool();
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public void setPersonGender(PersonGender personGender) {
        this.personGender = personGender;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDonatingTerms(Set<Term> donatingTerms) {
        this.donatingTerms = donatingTerms;
    }

    public void setWorkingTerms(Set<Term> workingTerms) {
        this.workingTerms = workingTerms;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSchool(String school) {
        this.school = school;
    }
    

    public Integer getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public PersonGender getPersonGender() {
        return personGender;
    }

    public String getSurname() {
        return surname;
    }

    public Set<Term> getDonatingTerms() {
        return donatingTerms;
    }

    public Set<Term> getWorkingTerms() {
        return workingTerms;
    }

    public Address getAddress() {
        return address;
    }

    public String getUuid() {
        return uuid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSchool() {
        return school;
    }

}
