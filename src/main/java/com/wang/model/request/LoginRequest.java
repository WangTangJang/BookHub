package com.wang.model.request;

import lombok.Data;

@Data
public class LoginRequest  {
    /**
     * 用户账号
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

}
