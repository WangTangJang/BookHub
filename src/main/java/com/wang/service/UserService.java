package com.wang.service;

import com.wang.model.User;

public interface UserService {

    /**
     * 用户注册
     * @param user 前端传入的用户信息
     * @return 注册结果
     */
    String UserRegister(User user);

    /**
     * 管理员注册
     * @param user 前端传入的用户信息
     * @return 注册结果
     */
    String AdminRegister(User user);

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

    User selectByUsername(String username);

    /**
     * 用户修改个人信息
     * @param user 修改后的部分信息
     */
    void updateUserProfile(User user);

    /**
     * 查找用户
     * @param user 用户的部分信息
     */
    void selectPro(User user);

    /**
     * 删除用户
     * @param id 用户的id
     */
    void delete(int id);
}
