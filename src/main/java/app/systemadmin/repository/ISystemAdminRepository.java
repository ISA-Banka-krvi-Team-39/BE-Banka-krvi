package app.systemadmin.repository;


import app.systemadmin.model.SystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ISystemAdminRepository extends JpaRepository<SystemAdmin, Integer> {

    @Query("select a from SystemAdmin a where a.person.personId = ?1")
    public SystemAdmin findOneByPerson(int personId);

}
