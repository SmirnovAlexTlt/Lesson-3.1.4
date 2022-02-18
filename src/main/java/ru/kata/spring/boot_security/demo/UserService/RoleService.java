package ru.kata.spring.boot_security.demo.UserService;

import ru.kata.spring.boot_security.demo.model.Role;
import java.util.List;
import java.util.Set;

public interface RoleService {

    void addRole(Set<Role> roles);

    Set<Role> findRoles(List<Long> roles);
}
