package com.centling.controller;

import java.io.Serializable;

public class LoginUserDto implements Serializable {
    private String userName;
    private String passWord;
    private String email;
    private String code;
    private String checkPassWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCheckPassWord() {
        return checkPassWord;
    }

    public void setCheckPassWord(String checkPassWord) {
        this.checkPassWord = checkPassWord;
    }
}
