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
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
public class UserDaoImpl implements UserDao {


    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @SuppressWarnings("unchecked")
    public List<UserModel> listUsers() {
        return entityManager.createQuery("select u from UserModel u", UserModel.class).getResultList();
    }

    @Override
    public UserModel showUserById(Long id) {
        return entityManager.find(UserModel.class, id);
    }


    @Override
    public void add(UserModel userModel, Set<Role> roles) {
        entityManager.persist(userModel);
    }


    @Override
    public void update(UserModel userModel, Set<Role> roles) {
        userModel.setRoles(roles);
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

    @Override
    public Set<Role> findRoles(List<Long> roles) {
        TypedQuery<Role> q = entityManager.createQuery("select r from Role r where r.id in :role", Role.class);
        q.setParameter("role", roles);
        return new HashSet<>(q.getResultList());
    }

}