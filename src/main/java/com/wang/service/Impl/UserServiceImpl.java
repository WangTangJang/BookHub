package com.wang.service.Impl;

import com.wang.mapper.UserMapper;
import com.wang.model.User;
import com.wang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper mapper;

    @Override
    public String UserRegister(User user) {

        if(mapper.selectPro(user)==null){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            String hashedPassword = passwordEncoder.encode(user.getPassword());

            user.setPassword(hashedPassword);
            user.setAccountStatus("offline");
            user.setRoles("user");
            mapper.insert(user);
            return "ok";
        }else {
            return "existed";
        }
    }

    @Override
    public String AdminRegister(User user) {
        if(mapper.selectPro(user)==null){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);
            user.setAccountStatus("offline");
            user.setRoles("Admin");
            mapper.insert(user);
            return "ok";
        }else {
            return "existed";
        }
    }
    @Override
    public String login(String username, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        User user = new User();
        System.out.println(user.getId());
        user.setUsername(username);
        user =  mapper.selectPro(user);

        // 检查账号状态是否正常
        if (user.getAccountStatus()!=null){
            if (!user.getAccountStatus().equals("online") && !user.getAccountStatus().equals("offline")){
                return user.getAccountStatus();
            }
        }
        // 匹配从数据库中取出的密码和输入的密码是否一致
        if(passwordEncoder.matches(password,user.getPassword())){
            return "ok";
        }else {
            return "password error";
        }
    }

    @Override
    public User selectById(int id) {
        return  mapper.select(id);
    }

    @Override
    public User selectByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        return mapper.selectPro(user);
    }

    @Override
    public void updateUserProfile(User user) {
        mapper.update(user);
    }

    @Override
    public void selectPro(User user) {
        mapper.selectPro(user);
    }

    @Override
    @Transactional
    public void delete(int id) {
        mapper.deleteOriginal(id);
        mapper.deleteDynamic(id);
    }
}
