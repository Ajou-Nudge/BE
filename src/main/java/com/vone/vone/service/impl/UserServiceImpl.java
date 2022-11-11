package com.vone.vone.service.impl;

import com.vone.vone.data.dao.UserDAO;
import com.vone.vone.data.entity.User;
import com.vone.vone.data.repository.UserRepository;
import com.vone.vone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserDAO userDAO)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDAO = userDAO;
    }

    public User create(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userDAO.create(user);
        return user;
    }
}