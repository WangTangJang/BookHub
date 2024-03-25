package com.wang.controller.admin;

import com.wang.model.User;
import com.wang.model.request.LoginRequest;
import com.wang.model.result.LoginResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AdminApiController {


    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest data) {
        String username = data.getUsername();
        String password = data.getPassword();

        if ("admin".equals(username) && "111111".equals(password)) {

            LoginResult result = new LoginResult();

            result.setMeta(new LoginResult.Meta(200,"登录成功"));
            result.setData(new LoginResult.Data(1, "7-66da5204d15b; d_c0=AODaYiXlRRiPTuPw7B4J"));
            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("登录失败");
        }
    }
    @GetMapping("/menus")
    public ResponseEntity<?> menus(){
        List<Map<String,Object>> menus = new ArrayList();
        Map<String,Object> userMenu = new HashMap<>();
        userMenu.put("authName","用户管理");

        List<Map<String,Object>> children = new ArrayList();
        Map<String,Object> children1 = new HashMap<>();
        children1.put("id",110);
        children1.put("authName","用户列表");
        children1.put("path","users");
        children1.put("children",new ArrayList<>());
        children1.put("order",null);
        children.add(children1);

        userMenu.put("children",children);
        userMenu.put("id","125");
        userMenu.put("order","1");
        userMenu.put("path","users");

        Map<String,Object> BookMenu = new HashMap<>();
        BookMenu.put("authName","书籍管理");

        children = new ArrayList();
        children1 = new HashMap<>();
        children1.put("id",111);
        children1.put("authName","书籍列表");
        children1.put("path","users");
        children1.put("children",new ArrayList<>());
        children1.put("order",null);
        children.add(children1);

        BookMenu.put("children",children);
        BookMenu.put("id","126");
        BookMenu.put("order","1");
        BookMenu.put("path","users");

        menus.add(userMenu);
        menus.add(BookMenu);

        Map<String,Object> result = new HashMap<>();
        result.put("data",menus);
        Map<String,Object> meta = new HashMap<>();
        meta.put("status",200);
        meta.put("message","请求成功");
        result.put("meta",meta);
        return ResponseEntity.ok(result);
    }
}
