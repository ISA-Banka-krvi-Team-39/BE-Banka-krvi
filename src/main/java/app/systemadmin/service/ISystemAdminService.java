package app.systemadmin.service;


import app.systemadmin.model.SystemAdmin;

public interface ISystemAdminService {

    public SystemAdmin create(SystemAdmin admin);
    public SystemAdmin findOneByPersonId(int personId);
    public SystemAdmin update(SystemAdmin admin);
}
