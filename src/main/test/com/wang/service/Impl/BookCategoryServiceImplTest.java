package com.wang.service.Impl;

import com.wang.service.BookCategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BookCategoryServiceImplTest {
    @Resource
    BookCategoryService service;

    @Test
    public void insert() {
        Map<String,Object> map = new HashMap<>();
        map.put("name","战争");
        service.insert(map);
        map.put("name","武侠");
        service.insert(map);
    }
}
