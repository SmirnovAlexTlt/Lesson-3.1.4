package ru.kata.spring.boot_security.demo.UserService;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    Role getRoleByID(Long id);

    Role getRoleByRole(String role);

    void addRole(Role role);

}
