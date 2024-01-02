package com.wang.service;

import com.wang.model.User;

public interface UserService {
    User login(String name ,String password);

    void register(User user);
}
