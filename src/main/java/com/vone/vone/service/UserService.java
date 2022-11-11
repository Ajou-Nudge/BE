package com.vone.vone.service;

import com.vone.vone.data.entity.User;

public interface UserService {

    User create(String name, String email, String password);

}