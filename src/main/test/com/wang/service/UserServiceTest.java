package com.wang.service;

import com.wang.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.apache.log4j.Logger;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserServiceTest {

    @Resource
    private UserService service;

    @Test
    public void login() {
        User user = new User();
        user.setUsername("wang");
        user.setPassword("123");
        service.login("xxx","123");
    }

    @Test
    public void register() {
        User user = new User();
        user.setUsername("xxx");
        user.setPassword("123");
        service.register(user);
    }
}

