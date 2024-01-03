package com.wang.service.Impl;

import com.wang.service.BookRatingService;
import com.wang.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BookRatingServiceImplTest {

    @Resource
    BookRatingService service;
    @Resource
    UserService userService;

    @Test
    public void rateBook() {
        userService.login("wang","123");
    }

}