package com.wang.service.Impl;

import com.wang.mapper.UserMapper;
import com.wang.model.Books;
import com.wang.model.User;
import com.wang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            return "注册成功";
        }else {
            return "此用户名已存在";
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
            return "注册成功";
        }else {
            return "此用户名已存在";
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
        // 检查账号是否存在
        if(user==null){
            return "账号不存在";
        }
        // 检查账号状态是否正常
        if (user.getAccountStatus()!=null){
            if (!user.getAccountStatus().equals("online") && !user.getAccountStatus().equals("offline")){
                return user.getAccountStatus();
            }
        }
        // 匹配从数据库中取出的密码和输入的密码是否一致
        if(passwordEncoder.matches(password,user.getPassword())){
            return "登录成功";
        }else {
            return "密码错误";
        }
    }

    @Override
    public String adminLogin(String username, String password) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        User user = new User();
        System.out.println(user.getId());
        user.setUsername(username);
        user =  mapper.selectPro(user);
        // 检查账号是否存在
        if(user==null){
            return "账号不存在";
        }

        // 检查账号状态是否正常
        if (user.getAccountStatus()!=null){
            if (!user.getAccountStatus().equals("online") && !user.getAccountStatus().equals("offline")){
                return user.getAccountStatus();
            }
        }
        if (!user.getRoles().equals("Admin")){
            return "此账号非管理员账号";
        }
        // 匹配从数据库中取出的密码和输入的密码是否一致
        if(passwordEncoder.matches(password,user.getPassword())){
            return "登录成功";
        }else {
            return "密码错误";
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
    public Page<User> findAllUser(Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        List<User> users = mapper.selectPage(offset, pageable.getPageSize());
        // 获取数据总量
        int total = mapper.count();

        // 创建PageImpl对象
        return new PageImpl<>(users, pageable, total);
    }

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            // 文件存放服务端的位置
            String rootPath = "C:\\Users\\Administrator\\Desktop\\ProxyZerl\\WebAppBuild\\nginx-1.25.4\\html\\img";
            File dir = new File(rootPath + File.separator + "avatar");
            if (!dir.exists())
                dir.mkdirs();
            // 写文件到服务器
            File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
            file.transferTo(serverFile);
            // 你就说存没存上去吧。。。
            return "http://localhost:8081/img/avatar/" +  file.getOriginalFilename();
        } catch (Exception e) {
            return "You failed to upload " +  file.getOriginalFilename() + " => " + e.getMessage();
        }
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

    @Override
    public int count(){
        List<User> userList =mapper.selectAll();
        return userList.size();
    }

    @Override
    public int countYesterday(){
        List<User> userList =mapper.selectAll();
        List<User> result = new ArrayList<>();

        java.util.Date now = new java.util.Date(System.currentTimeMillis());
        // 昨天的时间
        java.util.Date yesterday = new java.util.Date(now.getTime() - 24 * 60 * 60 * 1000);

        for (User user:userList){
            if (user.getCreateDate().after(yesterday)){
                result.add(user);
            }
        }
        return  result.size();
    }
}
