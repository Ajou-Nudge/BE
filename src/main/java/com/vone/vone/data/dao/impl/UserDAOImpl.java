package com.vone.vone.data.dao.impl;

import com.vone.vone.data.dao.UserDAO;
import com.vone.vone.data.entity.User;
import com.vone.vone.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDAOImpl implements UserDAO {
    public final UserRepository userRepository;

    @Autowired
    public UserDAOImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }


    @Override
    public User create(User user) {
        User savedUser = userRepository.save(user);

        return savedUser;
    }
}
