package app.center.model;

import app.person.model.Address;
import app.person.model.Person;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer termId;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @OneToOne(mappedBy = "term", fetch = FetchType.EAGER)
    private Terms terms;

    public Term()
    {

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Term term = (Term) o;
        return date.equals(term.date) && time.equals(term.time) && duration == term.duration;
    }


    public Term(Integer termId, LocalDate date, LocalTime time, Integer duration)
    {
        this.termId = termId;
        this.date = date;
        this.time = time;
        this.duration = duration;
    }

    public Integer getTermId(){
        return termId;
    }

    public LocalDate getDate(){
        return date;
    }

    public LocalTime getTime(){
        return time;
    }

    public Integer getDuration(){
        return duration;
    }







}
