package ru.kata.spring.boot_security.demo.UserService;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.UserDao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void addRole(Set<Role> roles) {
        roleDao.addRole(roles);
    }

    @Override
    public Set<Role> findRoles(List<Long> roles) {
        return roleDao.findRoles(roles);
    }
}
