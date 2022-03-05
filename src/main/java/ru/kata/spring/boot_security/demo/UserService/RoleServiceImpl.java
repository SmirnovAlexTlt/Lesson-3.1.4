package ru.kata.spring.boot_security.demo.UserService;


import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.Repostory.RoleRepository;
import ru.kata.spring.boot_security.demo.model.Role;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }


    @Override
    public Set<Role> findRoles(List<Long> roles) {
        return new HashSet<>(roleRepository.findAllById(roles));
    }
}
