package ru.kata.spring.boot_security.demo.Repostory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.UserModel;
import java.util.List;

@Repository
public interface UserModelRepository extends CrudRepository<UserModel,Long> {
    List<UserModel> findAll();
    UserModel findUserById(Long id);
    UserModel findUserByEmail(String email);
}
