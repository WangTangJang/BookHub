package com.wang.controller;

import com.wang.model.Books;
import com.wang.model.User;
import com.wang.service.BookRatingsService;
import com.wang.service.BooksService;
import com.wang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    // 不建议使用？
    @Autowired
    private UserService userService;

    @Autowired
    private BookRatingsService bookRatingsService;

    @Autowired
    private BooksService booksService;

    /**
     * 注册
     * @return 注册结果
     */
    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody Map<String, Object> data) {
        // 获取前端传来的数据
        User user = new User();
        user.setUsername(data.get("username").toString());
        user.setPassword(data.get("password").toString());
        user.setEmail(data.get("email").toString());
        // 调用service层的方法，进行注册，返回注册结果
        String result = userService.UserRegister(user);
        // 判断注册结果
        if (result.equals("注册成功")) {
            return ResponseEntity.ok(Map.of("message", result));
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
    /**
     * 登录
     * @param data 用户名和密码
     * @param session 会话
     * @param model 模型
     * @return 登录结果
     */
    @PostMapping( value = "/login")
    public  ResponseEntity<?> login(@RequestBody Map<String, String> data, HttpSession session, Model model){
        String username = data.get("username");
        String password = data.get("password");
        String result = userService.login(username, password); // 登录结果
        // 判断登录结果
        if(result.equals("登录成功")){ // 登录成功
            User user = userService.selectByUsername(username); // 查询用户
            session.setAttribute("user",user); // 保存用户
            return ResponseEntity.ok(Map.of("message",result));
        }else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 检查用户是否登录
     * @param session 会话
     * @return isLoggedIn
     */
    @GetMapping("/checkLogin")
    public ResponseEntity<?> checkLogin(HttpSession session) {
        // 获取session中的用户
        User user = (User) session.getAttribute("user");
        // 判断用户是否登录
        if (user != null) {
            return ResponseEntity.ok(Map.of("isLoggedIn", true));
        } else {
            return ResponseEntity.ok(Map.of("isLoggedIn", false));
        }
    }
    /**
     * 退出登录
     * @param session 会话
     * @return 退出结果
     */
    @PostMapping (value = "/logout")
    public ResponseEntity<?>  logout(HttpSession session){
        // 判断用户是否登录
        if (session.getAttribute("user")!=null){ // 用户已登录
            // 移除用户
            session.removeAttribute("user");
            // 返回退出成功
            return ResponseEntity.ok(Map.of("message", "退出成功"));
        }else {
            // 返回退出失败
            return ResponseEntity.badRequest().body("退出失败");
        }
    }
}
