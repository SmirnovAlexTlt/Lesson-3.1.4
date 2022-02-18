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
    private String name;

    @Column
    @NotEmpty(message = "LastName should not be empty")
    @Size(min = 2, max = 20, message = "LastName must be between 2 and 20")
    private String lastName;

    @Column
    private int age;

    @Column
    @NotEmpty(message = "Country must not be empty")
    @Size(min = 5, max = 20, message = "Country must be between 5 and 20")
    private String country;

    @Column
    @Size(min = 1, max = 15, message = "Phone must be between 1 and 15")
    private String phoneNumber;


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

    public UserModel(String name, String lastName, int age,
                     String country, String phoneNumber, String email, String userPassword) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = userPassword;
    }

    public UserModel(String name, String lastname, int age,
                     String country, String phoneNumber, String email, String password, Set<Role> roles) {
        this(name, lastname, age, country, phoneNumber, email, password);
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

