package app.systemadmin.service;

import app.systemadmin.model.SystemAdmin;
import app.systemadmin.repository.ISystemAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemAdminService implements ISystemAdminService {

    @Autowired
    private ISystemAdminRepository systemAdminRepository;

    @Override
    public SystemAdmin create(SystemAdmin admin) {
        return systemAdminRepository.save(admin);
    }

    @Override
    public SystemAdmin findOneByPersonId(int personId) {
        return systemAdminRepository.findOneByPerson(personId);
    }

    @Override
    public SystemAdmin update(SystemAdmin admin) {
        return systemAdminRepository.save(admin);
    }
}
