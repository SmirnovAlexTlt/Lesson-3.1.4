package ru.kata.spring.boot_security.demo.UserService;

import ru.kata.spring.boot_security.demo.UserDao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    public Role getRoleByID(Long id) {
        return roleDao.getRoleByID(id);
    }

    @Override
    public Role getRoleByRole(String role) {
        return roleDao.getRoleByRole(role);
    }

    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }
}
