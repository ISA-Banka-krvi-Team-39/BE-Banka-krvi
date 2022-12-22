package app.systemadmin.dto;

import app.person.model.Person;
import app.systemadmin.model.SystemAdmin;

public class SystemAdminDTO {

    private Integer adminId;



    private Person person;
    private Boolean wasLoggedIn;

    public SystemAdminDTO(SystemAdmin admin){
        this.adminId = admin.getAdminId();
        this.person = admin.getPerson();
        this.wasLoggedIn = admin.getWasLoggedIn();
    }

    public Integer getAdminId() {
        return adminId;
    }

    public Boolean getWasLoggedIn() {
        return wasLoggedIn;
    }

    public Person getPerson() {
        return person;
    }
}
