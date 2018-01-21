package com.centling.service;

import com.alibaba.fastjson.JSONObject;
import com.centling.config.ApplicationProperties;
import com.centling.config.WebConfigurer;
import com.centling.controller.JWTToken;
import com.centling.domain.User;
import com.centling.mapper.blog.UserMapper;
import com.centling.security.SecurityUtils;
import com.centling.security.jwt.TokenProvider;
import com.centling.utils.Result;
import com.centling.utils.blogUtils.HttpUtils;
import lombok.extern.log4j.Log4j;
import org.slf4j.LoggerFactory;
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
import java.util.logging.Logger;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    private final org.slf4j.Logger log = LoggerFactory.getLogger(WebConfigurer.class);

    private final static String CLIENT_ID = "3191489564";
    private final static String CLIENT_SERCRET = "bad088883841d9d1be1a59011ac98fd7";
    private final static String GET_TOKEN_URL = "https://api.weibo.com/oauth2/access_token";
    private final static String REDIRECT_URI = "http://zhixiang.org.cn/%23/login";
    private final static String GET_USER_INFO = "https://api.weibo.com/2/users/show.json";
    public Result weiboLogin(String code) {
        log.info("weibologin statrt---------------------------------------");
        User user=null;
        try {
            String access_token = "";
            String uid = "";
            String url=GET_TOKEN_URL+"?client_id="+CLIENT_ID+"&client_secret="+CLIENT_SERCRET+"&grant_type=authorization_code&redirect_uri="+REDIRECT_URI+"&code="+code;
            JSONObject token = HttpUtils.post(url);
            log.info("weibologin token"+token.toString()+"---------------------------------------");
            if(token.toString().indexOf("error_code")>0){
                return new Result(400,"登录失败");
            }
            access_token = token.getString("access_token");

            uid = token.getString("uid");
            url=GET_USER_INFO+"?access_token="+access_token+"&uid="+uid;
            JSONObject userInfo= HttpUtils.get(url);
            log.info("weibologin userInfo"+userInfo.toString()+"---------------------------------------");
           user=new User(userInfo.getString("idstr"),userInfo.getString("screen_name"),1,userInfo.getString("location"),userInfo.getString("profile_image_url"),userInfo.getString("gender"),userInfo.getString("description"));
            if(userMapper.selectBySourceAndUid(1,user.getUid())==0){
                insert(user);
            }
            user= userMapper.selectUserByNameAndUid(user.getUserName(),user.getUid());

        }catch (Exception e){
            log.info("weiboyichang+++++++++++++++++++++++++++++++++++++++++++++++");
            e.printStackTrace();
        }
        return new Result(user);
    }
    public Result cheackName(String name, int id) {

        return new Result();
    }

    public void insert(User user) {
        user.setCreatedTime(System.currentTimeMillis());
        userMapper.insert(user);
    }


}
