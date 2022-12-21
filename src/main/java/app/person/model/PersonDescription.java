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

    @Column(name="month_period", unique=false, nullable=false)
    private boolean month_period;

    @Column(name="tooth", unique=false, nullable=false)
    private boolean tooth;

    @Column(name="tatoo", unique=false, nullable=false)
    private boolean tatoo;

    public PersonDescription()
    {

    }
    public PersonDescription(boolean sick, boolean antibiotics, boolean month_period, boolean tatoo,boolean tooth,boolean pressure,boolean infections, String kilogram)
    {
        this.sick = sick;
        this.antibiotics = antibiotics;
        this.month_period = month_period;
        this.tatoo = tatoo;
        this.tooth = tooth;
        this.pressure = pressure;
        this.infections = infections;
        this.kilograms = kilogram;
    }

    public String getKilograms() {
        return kilograms;
    }

    public Integer getPersonDescriptionId() {
        return personDescriptionId;
    }

    public boolean isAntibiotics() {
        return antibiotics;
    }

    public boolean isInfections() {
        return infections;
    }

    public boolean isMonth_period() {
        return month_period;
    }

    public boolean isPressure() {
        return pressure;
    }

    public boolean isSick() {
        return sick;
    }

    public boolean isTatoo() {
        return tatoo;
    }

    public boolean isTooth() {
        return tooth;
    }
}
