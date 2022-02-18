package ru.kata.spring.boot_security.demo.UserDao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public void addRole(Set<Role> roles) {
        entityManager.persist(roles);
    }

    @Override
    public Set<Role> findRoles(List<Long> roles) {
        TypedQuery<Role> q = entityManager.createQuery("select r from Role r where r.id in :role", Role.class);
        q.setParameter("role", roles);
        return new HashSet<>(q.getResultList());
    }
}
