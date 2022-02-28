package ru.kata.spring.boot_security.demo.UserService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.Repostory.UserModelRepository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.UserModel;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserModelRepository userModelRepository;


    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder, UserModelRepository userModelRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userModelRepository = userModelRepository;
    }

    @Override
    public List<UserModel> getAllUser() {
        return userModelRepository.findAll();
    }

    @Override
    public UserModel findUserById(Long id) {
        return userModelRepository.findUserById(id);
    }

    @Transactional
    @Override
    public void addUser(UserModel userModel, Set<Role> roles) {
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userModel.setRoles(roles);
        userModelRepository.save(userModel);

    }

    @Transactional
    @Override
    public void updateUser(UserModel userModel,Set<Role> roles) {
        String passwordEncode = userModel.getPassword();
        String password = findUserById(userModel.getId()).getPassword();
        if (passwordEncode.equals(password)){
            userModel.setPassword(password);
        }else {
            if (passwordEncoder.matches(passwordEncode,password)){
                userModel.setPassword(password);
            }else {
                userModel.setPassword(passwordEncoder.encode(passwordEncode));
            }
        }
        userModel.setRoles(roles);
        userModelRepository.save(userModel);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        userModelRepository.deleteById(id);
    }

    @Override
    public UserModel showUserByEmail(String email) {
        return userModelRepository.findUserByEmail(email);
    }

}
