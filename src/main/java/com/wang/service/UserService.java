package com.wang.service;

import com.wang.model.User;

public interface UserService {

    /**
     * 用户注册
     * @param user 前端传入的用户信息
     */
    void register(User user);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 用户密码
     * @return 登录结果
     */
    String login(String username,String password);

    /**
     * 查找用户
     * 此接口id的值直接查找
     * @param id 用户的id的值
     * @return 找到的用户
     */
    User selectById(int id);
}
