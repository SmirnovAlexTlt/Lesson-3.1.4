package ru.kata.spring.boot_security.demo.UserDao;

import ru.kata.spring.boot_security.demo.model.UserModel;
import java.util.List;


public interface UserDao {

    List<UserModel> listUsers();

    UserModel showUserById(Long id);

    void add(UserModel userModel);

    void update(UserModel userModel);

    void delete(Long id);

    UserModel showUserByEmail(String email);
}