package ru.kata.spring.boot_security.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserModel implements UserDetails {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20")
    private String firstName;

    @Column
    @NotEmpty(message = "LastName should not be empty")
    @Size(min = 2, max = 20, message = "LastName must be between 2 and 20")
    private String lastName;

    @Column
    private int age;

    @Column(name = "email", unique = true)
    @Email(message = "Email must be valid")
    private String email;

    @Column
    @NotEmpty(message = "Password must not be empty")
    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public UserModel() {
    }

    public UserModel(String firstName, String lastName, int age,
                     String email, String userPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = userPassword;
    }

    public UserModel(String firstName, String lastname, int age,
                     String email, String password, Set<Role> roles) {
        this(firstName, lastname, age, email, password);
        this.roles = roles;
    }

    public void setOneRole(Role role) {
        if (roles == null) {
            roles = new HashSet<>();
        }
        roles.add(role);
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

