package app.person.model;

import app.shared.model.Address;

import javax.persistence.*;

@Entity
public class PersonDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer personDescriptionId;

    @Column(name="kilograms", unique=false, nullable=false)
    private String kilograms;

    @Column(name="sick", unique=false, nullable=false)
    private boolean sick;

    @Column(name="infections", unique=false, nullable=false)
    private boolean infections;

    @Column(name="pressure", unique=false, nullable=false)
    private boolean pressure;

    @Column(name="antibiotics", unique=false, nullable=false)
    private boolean antibiotics;

    @Column(name="period", unique=false, nullable=false)
    private boolean period;

    @Column(name="tooth", unique=false, nullable=false)
    private boolean tooth;

    @Column(name="tatoo", unique=false, nullable=false)
    private boolean tatoo;

    public PersonDescription()
    {

    }


}
