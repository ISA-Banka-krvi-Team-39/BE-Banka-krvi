package app.center.model;

import app.person.model.Person;
import app.user.model.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Terms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer termsId;

    @ManyToMany( cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
    @JoinTable(name = "working", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "terms_id"))
    private Set<Person> persons = new HashSet<Person>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "centerId")
    private Center center;


    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "term_id", referencedColumnName = "termId")
    private Term term;

    public Terms()
    {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Terms term = (Terms) o;
        return persons.equals(term.persons) && term.equals(term.term);
    }


    public Terms(Integer termId, Set<Person> persons, Term term)
    {
        this.termsId = termId;
        this.persons = persons;
        this.term = term;
    }

    public Integer getTermId(){
        return termsId;
    }

    public Set<Person> getPersons(){
        return persons;
    }

    public Term getTerm(){
        return term;
    }




}
