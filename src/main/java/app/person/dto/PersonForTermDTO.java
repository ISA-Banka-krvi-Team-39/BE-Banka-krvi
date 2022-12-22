package app.person.dto;

import app.person.model.Person;
import app.person.model.PersonType;
import app.shared.model.Address;

public class PersonForTermDTO {

        private Integer Id;
        private String Name;

        public PersonForTermDTO()
        {

        }

        public PersonForTermDTO(Person person)
        {
            this(person.getPersonId(),person.getName() + "  " + person.getSurname());
        }

        public PersonForTermDTO(Integer id, String name) {
            Id = id;
            Name = name;
        }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
