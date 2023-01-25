package app.user.service;

import app.user.model.Role;

import java.util.List;

public interface IRoleService {
    Role findById(Long id);
    List<Role> findByName(String name);
    List<Role> findAll();

}
