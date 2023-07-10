package com.example.jpasigninsignup.service;

import com.example.jpasigninsignup.dao.RoleDao;
import com.example.jpasigninsignup.dao.UserDao;
import com.example.jpasigninsignup.entity.Role;
import com.example.jpasigninsignup.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void signUp(User user){
        System.out.println("----------:"+user);
        Role role = new Role();
        role.setName("ROLE_USER");

        Role userRole=roleDao.findRoleByName("ROLE_USER")
                .orElse(role);
        user.addRole(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);

    }


}
