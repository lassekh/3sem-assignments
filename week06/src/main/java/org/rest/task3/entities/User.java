package org.rest.task3.entities;

import jakarta.persistence.*;
import lombok.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "users")
public class User implements ISecurityUser{
    @Id
    private String username;
    private String password;
    @ManyToMany
    @ToString.Exclude
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_name", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(name = "role_name", referencedColumnName = "rolename"))
    private Set<Role> roles = new HashSet<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        //a salt is added to the password for extra security
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public Set<String> getRolesAsStrings() {
        Set<String> roleNames = new HashSet<>();
        roles.forEach(role -> roleNames.add(role.getRolename()));
        return roleNames;
    }

    @Override
    public boolean verifyPassword(String pw) {
        //First input the password received, then match it against the encrypted password
        return BCrypt.checkpw(pw, this.password);
    }

    public void addRole(Role role)
    {
        this.roles.add(role);
        role.getUsers().add(this);
    }

    @Override
    public void removeRole(String role) {
        roles.removeIf(r -> r.getRolename().equals(role));
    }
}
