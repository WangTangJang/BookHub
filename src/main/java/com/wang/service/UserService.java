package com.wang.service;

import com.wang.model.User;

public interface UserService {
    void register(User user);

    String login(String username,String password);
}
