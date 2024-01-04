package com.wang.service.Impl;

import com.wang.mapper.UserMapper;
import com.wang.model.User;
import com.wang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper mapper;

    @Override
    public void register(User user) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());

        LocalDate localDate = LocalDate.now();

        user.setPassword(hashedPassword);
        user.setCreateDate(Date.valueOf(localDate));
        user.setAccountStatus("offline");
        user.setRoles("user");
        user.setExperience(0);
        user.setLevel(1);
        user.setMembershipStatus("non-member");
        mapper.insert(user);
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
        mapper.login(username, hashedPassword);
        return "ok";
    }

    @Override
    public User selectById(int id) {
        return  mapper.select(id);
    }
}
