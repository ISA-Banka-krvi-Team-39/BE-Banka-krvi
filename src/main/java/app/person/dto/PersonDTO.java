package app.person.dto;

import app.person.model.Person;
import app.person.model.PersonType;
import app.shared.model.Address;

public class PersonDTO {

    private Integer personId;
    private String name;
    private String surname;
    private PersonType personType;
    private Address address;
    private String phoneNumber;
    private String school;

    public PersonDTO()
    {

    }

    public PersonDTO(Person person)
    {
        this(person.getPersonId(),person.getName(),person.getSurname(),person.getPersonType(),person.getAddress(),person.getPhoneNumber(),person.getSchool());
    }

    public PersonDTO(Integer personId,String name,String surname,PersonType personType,Address address,String phoneNumber,String school)
    {
        this.personId = personId;
        this.name = name;
        this.surname = surname;
        this.personType = personType;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.school = school;
    }

    public String getName()
    {
        return name;
    }
    public Integer getPersonId(){return personId;}
    public String getSurname()
    {
        return surname;
    }
    public PersonType getPersonType()
    {
        return personType;
    }
    public Address getAddress()
    {
        return address;
    }
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public String getSchool()
    {
        return school;
    }
}
