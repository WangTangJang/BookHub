package com.wang.model;

import lombok.Data;

import java.sql.Date;
@Data
public class Users {


    private long id;
    private String username;
    private String password;

    private String securityQuestion;
    private String lastLoginIpAddress;
    private String lastLoginDate;
    //private String accountStatus;
    //private java.sql.Date createDate;


    public Users() {
        System.out.println("1111111111111111");
    }



}
