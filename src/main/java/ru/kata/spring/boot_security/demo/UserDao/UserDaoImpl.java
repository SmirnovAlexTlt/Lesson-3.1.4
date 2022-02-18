package ru.kata.spring.boot_security.demo.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.UserModel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserModel> listUsers() {
        return entityManager.createQuery("select u from UserModel u", UserModel.class).getResultList();
    }

    @Override
    public UserModel showUserById(Long id) {
        return entityManager.find(UserModel.class, id);
    }

    @Override
    public void add(UserModel userModel) {
        entityManager.persist(userModel);
    }

    @Override
    public void update(UserModel userModel) {
        entityManager.merge(userModel);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(showUserById(id));
    }

    @Override
    public UserModel showUserByEmail(String email) {
        return (UserModel) entityManager.createQuery("select u from UserModel u where u.email = :email")
                .setParameter("email", email)
                .getSingleResult();
    }
}