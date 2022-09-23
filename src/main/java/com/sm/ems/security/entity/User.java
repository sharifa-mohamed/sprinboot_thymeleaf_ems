package com.sm.ems.security.entity;

import com.sm.ems.util.MethodOrder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "username", name = "username_unique"))
public class User extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(sequenceName = "user_sequence", name = "user_sequence", initialValue = 5)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d\\w\\W]{8,}$",
            message = "Password must be minimum 8 characters with at least one upper case & lower case letter and one number")
    private String password;
    @Transient
    private String confirmPassword;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    Set<Role> roles = new HashSet<>();


    @PreRemove
    public void removeAllRoles() {
        this.roles.clear();
    }

    @MethodOrder.Order(value = 1)
    public Long getId() {
        return id;
    }

    @MethodOrder.Order(value = 2)
    public String getFirstName() {
        return firstName;
    }

    @MethodOrder.Order(value = 3)
    public String getLastName() {
        return lastName;
    }

    @MethodOrder.Order(value = 4)
    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }


    public Set<Role> getRoles() {
        return roles;
    }


    @MethodOrder.Order(value = 5)
    public List<String> getRoles_() {
        return roles.stream().map(c -> c.getName()).toList();
    }
}
