package ru.kata.spring.boot_security.demo.UserService;


import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.UserModel;
import java.util.List;
import java.util.Set;


public interface UserService {

    List<UserModel> getAllUser();
    void addUser(UserModel userModel,Set<Role> roles);
    void deleteById(Long id);
    UserModel findUserById(Long id);
    void updateUser(UserModel userModel,Set<Role> roles);
    UserModel showUserByEmail(String email);

}

