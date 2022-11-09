package app.center.model;

import app.person.model.Address;
import app.person.model.Person;
import app.user.model.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Center {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer centerId;

    @Column(name="name", unique=false, nullable=false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private Address address;

    @Column(name="description", unique=false, nullable=false)
    private String description;

    @Column(name="avg_grade", unique=false, nullable=false)
    private Float avg_grade;

    @OneToMany(mappedBy = "termsId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Terms> terms = new HashSet<Terms>();

    @OneToMany(mappedBy = "personId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Person> persons = new HashSet<Person>();

    public Center()
    {

    }
    public Center(Center center)
    {
        this(center.getCenterId(), center.getName(), center.address,center.description,center.avg_grade,center.terms,center.persons);
    }

    public Center(Integer centerId, String name, Address address, String description, Float avg_grade, Set<Terms> terms, Set<Person> persons)
    {
        this.centerId = centerId;
        this.name = name;
        this.address = address;
        this.description = description;
        this.avg_grade = avg_grade;
        this.terms = terms;
        this.persons = persons;
    }

    public Integer getCenterId(){
        return centerId;
    }

    public String getName(){
        return name;
    }

    public Address getAddress(){
        return address;
    }

    public String getDescription(){
        return description;
    }

    public Float getAvg_grade(){
        return avg_grade;
    }

    public Set<Terms> getTerms(){
        return terms;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Center center = (Center) o;
        return centerId == center.centerId && Objects.equals(name,center.name) && Objects.equals(description, center.description) && Objects.equals(terms, center.terms) && Objects.equals(address, center.address) && Objects.equals(persons, center.persons);
    }


}
