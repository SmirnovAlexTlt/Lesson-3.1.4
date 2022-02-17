package ru.kata.spring.boot_security.demo.UserService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.UserDao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.UserModel;


import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder passwordEncoder;


    private final UserDao userDao;

    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder, UserDao userDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }


    @Override
    public List<UserModel> listUsers() {
        return userDao.listUsers();
    }


    @Override
    public UserModel showUserById(Long id) {
        return userDao.showUserById(id);
    }


    @Override
    public void add(UserModel userModel, Set<Role> roles) {
        userModel.setRoles(roles);
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userDao.add(userModel, roles);
    }


    @Override
    public void update(UserModel userModel, Set<Role> roles) {
        userModel.setRoles(roles);
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userDao.update(userModel, roles);
    }


    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public UserModel showUserByEmail(String email) {
        return userDao.showUserByEmail(email);
    }

    @Override
    public Set<Role> findRoles(List<Long> roles) {
        return userDao.findRoles(roles);
    }
}
