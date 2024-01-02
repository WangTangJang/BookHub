package com.wang.service.Impl;

import com.wang.mapper.UserMapper;
import com.wang.model.User;
import com.wang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper mapper;

    public User login(String name, String password) {
        return mapper.login(name,password);
    }

    public void register(User user) {
        mapper.register(user);
    }
}
