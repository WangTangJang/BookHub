package com.wang.controller.admin;

import com.wang.model.User;
import com.wang.model.request.LoginRequest;
import com.wang.model.result.LoginResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest data) {
        String username = data.getUsername();
        String password = data.getPassword();

        if ("admin".equals(username) && "111111".equals(password)) {

            LoginResult result = new LoginResult();
            result.setCode(20000);
            result.setMsg("登录成功");
            result.setData(new LoginResult.Data(1, "7-66da5204d15b; d_c0=AODaYiXlRRiPTuPw7B4J"));
            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("error login");
        }
    }
    @GetMapping(value = "/info")
    public ResponseEntity<?> info() {
        Map<String, Object> result = new HashMap<>();

        Map<String, Object> data = new HashMap<>();
        data.put("roles", "editor");
        data.put("introduction", "I am a super administrator");
        data.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        data.put("name", "Super Admin");

        result.put("data", data);

        result.put("code", 20000);

        return ResponseEntity.ok().body(result);
    }
    @PostMapping(value = "/logout")
    public ResponseEntity<?> logout() {
        LoginResult result = new LoginResult();
        result.setCode(20000);
        return ResponseEntity.ok().body(result);
    }
}
