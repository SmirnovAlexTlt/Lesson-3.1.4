package ru.kata.spring.boot_security.demo.UserDao;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.UserModel;

import java.util.List;
import java.util.Set;

public interface UserDao {

    List<UserModel> listUsers();

    UserModel showUserById(Long id);

    void add(UserModel userModel, Set<Role> roles);

    void update(UserModel userModel, Set<Role> roles);

    void delete(Long id);

    UserModel showUserByEmail(String email);

    Set<Role> findRoles(List<Long> roles);
}