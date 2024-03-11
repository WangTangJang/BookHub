package com.wang.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/book")
public class AdminBookController {

    @RequestMapping("/list")
    public ResponseEntity<?> list() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 20000);
        map.put("id", 1);
        map.put("display_time", "@datetime");
        map.put("pageviews", "@integer(300, 5000");
        map.put("name", "三国演义");
        map.put("author", "罗贯中");
        return ResponseEntity.ok().body(map);
    }
}
