package app.person.service;

import app.medical_staff.model.MedicalStaff;
import app.medical_staff.repository.IMedicalStaffRepository;
import app.person.model.Person;
import app.person.model.PersonType;
import app.person.repository.IPersonRepository;
import app.user.model.Role;
import app.user.model.User;
import app.user.repository.IRoleRepository;
import app.user.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements IPersonService {
    @Autowired
    private IPersonRepository personRepository;
    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IMedicalStaffRepository medicalStaffRepository;
    
    public List<Person> findAll() {
        return personRepository.findAll();
    }
    @Override
    public Person create(Person person) { return personRepository.save(person); }
    @Override
    public Person findOne(int id){
        return personRepository.findOneByPersonId(id);
    }
    @Override
    public boolean checkUidUniqueness(String uid) {
        return personRepository.findOneByUid(uid)==null;
    }
    @Override
    public Person update(Person person) { return personRepository.save(person); }
    @Override
    public List<Person> findAvailableAdmins(int id){
        List<Person> personList = personRepository.findAll();
        List<Role> roleList = roleRepository.findAll();
        List<User> userList = new ArrayList<>();
        List<Person> retList = new ArrayList<>();
        List<Person> ret2List = new ArrayList<>();
        List<MedicalStaff> medicalStaffs = medicalStaffRepository.findAll();
        for(Person person : personList)
        {
            userList.add(userRepository.findOneByPersonId(person.getPersonId()));
        }
        for(User user : userList)
        {
            for(Role role : user.getRoles())
            {
                if(role.getId() == 1)
                {
                    retList.add(user.getPerson());
                    break;
                }

            }
        }
        int i = 0;
        int j = 0;
        int n = medicalStaffs.size();
        for(Person p : retList)
        {
            if(!medicalStaffs.contains(p.getPersonId()))
                ret2List.add(p);

//            for(MedicalStaff medicalStaff : medicalStaffs)
//            {
//                if(p.getPersonId() == medicalStaff.getPerson().getPersonId())
//                {
//
//                    i = 1;
//
//                    break;
//                }
//
//                if(j == n - 1)
//                    if(i == 0)
//                        ret2List.add(p);
//
//                j++;
//            }
        }
        for(Person p : ret2List)
        {
            System.out.println(p.getName() + " " + p.getSurname());
        }
        List<Person> ret3List = findScheduledAdmins(id);
        List<Person> ret4List = new ArrayList<>();
        for(Person p : ret2List)
        {
            if(!ret3List.contains(p))
            {
                ret4List.add(p);
            }
        }

        System.out.println("sizeav " + ret4List.size());
        return ret4List;

    }
    public List<Person> findAdmins(){return personRepository.findAdmins();}

    @Override
    public List<Person> findScheduledAdmins(int id) {
        List<Person> personList = personRepository.findAll();
        List<Role> roleList = roleRepository.findAll();
        List<User> userList = new ArrayList<>();
        List<Person> retList = new ArrayList<>();
        List<Person> ret2List = new ArrayList<>();
        List<MedicalStaff> medicalStaffs = medicalStaffRepository.findAll();
        for (Person person : personList) {
            userList.add(userRepository.findOneByPersonId(person.getPersonId()));
        }


        for (User user : userList) {
            for (Role role : user.getRoles()) {
                if (role.getId() == 1) {
                    retList.add(user.getPerson());
                    break;
                }

            }
        }
        int i = 0;
        int j = 0;
        int n = medicalStaffs.size();
        for (Person p : retList) {
            for (MedicalStaff medicalStaff : medicalStaffs) {
                if(medicalStaff.getWorkingCenter().getCenterId() == id)
                if (p.getPersonId() == medicalStaff.getPerson().getPersonId()) {
                    ret2List.add(p);
                    break;
                }
            }
        }
        System.out.println("size " + ret2List.size());
        return ret2List;
    }
}
