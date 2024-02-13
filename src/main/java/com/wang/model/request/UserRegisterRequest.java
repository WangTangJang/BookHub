package com.wang.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterRequest implements Serializable {
    private final static long serialVersionUID = 1L;
    private String username_r;

    private String password_r;

    private String checkPassword;

    private String email_r;
}
