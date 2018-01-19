package com.centling.service;

import com.alibaba.fastjson.JSONObject;
import com.centling.config.ApplicationProperties;
import com.centling.controller.JWTToken;
import com.centling.domain.User;
import com.centling.mapper.blog.UserMapper;
import com.centling.security.SecurityUtils;
import com.centling.security.jwt.TokenProvider;
import com.centling.utils.Result;
import com.centling.utils.blogUtils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    private final static String CLIENT_ID = "3191489564";
    private final static String CLIENT_SERCRET = "bad088883841d9d1be1a59011ac98fd7";
    private final static String GET_TOKEN_URL = "https://api.weibo.com/oauth2/access_token";
    private final static String REDIRECT_URI = "http://zhixiang.org.cn";
    private final static String GET_USER_INFO = "https://api.weibo.com/2/users/show.json";

    public Result weiboLogin(String code) {
        try {
            String access_token = "";
            String uid = "";
            String url=GET_TOKEN_URL+"?client_id="+CLIENT_ID+"&client_secret="+CLIENT_SERCRET+"&grant_type=authorization_code&redirect_uri="+REDIRECT_URI+"&code="+code;
            JSONObject token = HttpUtils.post(url);
            access_token = token.getString("access_token");
            uid = token.getString("uid");
            url=GET_USER_INFO+"?access_token="+access_token+"&uid="+uid;
            JSONObject userInfo= HttpUtils.get(url);
            User user=new User(userInfo.getString("idstr"),userInfo.getString("screen_name"),1,userInfo.getString("location"),userInfo.getString("profile_image_url"),userInfo.getString("gender"),userInfo.getString("description"));
            if(userMapper.selectByNameAndUid(user.getUserName(),user.getUid())==0){
                insert(user);
            }
            user= userMapper.selectUserByNameAndUid(user.getUserName(),user.getUid());
            return new Result(user);
        }catch (Exception e){
            e.printStackTrace();
        }
       return new Result(300,"微博登录失败");
    }
    public Result cheackName(String name, int id) {

        return new Result();
    }

    public void insert(User user) {
        user.setCreatedTime(System.currentTimeMillis());
        userMapper.insert(user);
    }


}
