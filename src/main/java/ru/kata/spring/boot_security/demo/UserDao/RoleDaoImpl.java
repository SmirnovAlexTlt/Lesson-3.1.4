package ru.kata.spring.boot_security.demo.UserDao;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.UserModel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Component
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    public RoleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

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
