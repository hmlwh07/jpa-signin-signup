package com.example.jpasigninsignup.entity;

import com.example.jpasigninsignup.validation.NamedNotAdmin;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NamedNotAdmin(message = "{validator.msg.user}")
    @Column(unique = true)
    private String username;
    private String password;
    @Transient
    private String repeatedPassword;
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles=new ArrayList<>();

    public void addRole(Role role){
        role.getUser().add(this);
        this.roles.add(role);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
