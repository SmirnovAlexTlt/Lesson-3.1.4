package ru.kata.spring.boot_security.demo.UserDao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select u from Role u", Role.class).getResultList();
    }

    @Override
    public Role getRoleByID(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role getRoleByRole(String role) {
        return entityManager.find(Role.class, role);
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }
}
