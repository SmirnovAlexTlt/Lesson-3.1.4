package ru.kata.spring.boot_security.demo.UserDao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;


public interface RoleDao {
    List<Role> getAllRoles();

    Role getRoleByID(Long id);

    Role getRoleByRole(String role);

    void addRole(Role role);

}
