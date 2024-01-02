package com.wang.mapper;

import com.wang.model.User;
import com.wang.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserMapperTest {

    @Autowired
    UserMapper mapper;

    @Resource
    UserService service;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("xxx");
        user.setPassword("123");
        mapper.insert(user);
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }

    @Test
    public void select() {
        mapper.select(1);
    }

    @Test
    public void login() {
        System.out.println(service.login("Jang","123"));

    }

    @Test
    public void register(){

        User user = new User();
        user.setUsername("Jang");
        user.setPassword("123");
        user.setCountry("china");
        user.setGender("female");
        user.setEmail("1424435163@qq.com");
        user.setDataOfBirth(Date.valueOf("2002-05-25"));
        user.setPhone("1755028****");
        user.setSecurityQuestion("我的故乡是哪里?");
        service.register(user);
    }
}