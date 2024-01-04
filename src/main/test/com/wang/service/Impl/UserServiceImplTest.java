package com.wang.service.Impl;

import com.wang.model.User;
import com.wang.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.sql.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceImplTest {


    @Resource
    UserService service;
    @Test
    public void register() {
        User user = new User();
        user.setUsername("王唐将");
        user.setPassword("123456");
        user.setPhone("13039423142");
        user.setDataOfBirth(Date.valueOf("2002-5-25"));
        user.setEmail("1424435163@qq.com");
        user.setGender("male");
        user.setCountry("china");
        System.out.println(service.register(user));
    }

    @Test
    public void login() {
    }

    @Test
    public void selectById() {
    }
}