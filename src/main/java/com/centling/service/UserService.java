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
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    private final org.slf4j.Logger log = LoggerFactory.getLogger(WebConfigurer.class);

    private final static String CLIENT_ID = "3191489564";
    private final static String QQCLIENT_ID = "101453375";
    private final static String CLIENT_SERCRET = "bad088883841d9d1be1a59011ac98fd7";
    private final static String QQCLIENT_SERCRET = "1a31c78316626051c29c27b2454c29c6";
    private final static String GET_TOKEN_URL = "https://api.weibo.com/oauth2/access_token";
    private final static String QQGET_TOKEN_URL = "https://graph.qq.com/oauth2.0/token";
    private final static String QQGET_OPEN_ID = "https://graph.qq.com/oauth2.0/me";
    private final static String REDIRECT_URI = "http://zhixiang.org.cn/%23/login";
    private final static String QQREDIRECT_URI = "http://zhixiang.org.cn/%23/qqLogin";
    private final static String GET_USER_INFO = "https://api.weibo.com/2/users/show.json";
    private final static String QQGET_USER_INFO = "https://graph.qq.com/user/get_user_info";

    public Result weiboLogin(String code) {
        log.info("weibologin statrt---------------------------------------");
        User user = null;
        try {
            String access_token = "";
            String uid = "";
            String url = GET_TOKEN_URL + "?client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SERCRET + "&grant_type=authorization_code&redirect_uri=" + REDIRECT_URI + "&code=" + code;
            JSONObject token = HttpUtils.post(url);
            log.info("weibologin token" + token.toString() + "---------------------------------------");
            if (token.toString().indexOf("error_code") > 0) {
                return new Result(400, "登录失败");
            }
            access_token = token.getString("access_token");

            uid = token.getString("uid");
            url = GET_USER_INFO + "?access_token=" + access_token + "&uid=" + uid;
            JSONObject userInfo = HttpUtils.get(url);
            log.info("weibologin userInfo" + userInfo.toString() + "---------------------------------------");
            user = new User(userInfo.getString("idstr"), userInfo.getString("screen_name"), 1, userInfo.getString("location"), userInfo.getString("profile_image_url"), userInfo.getString("gender"), userInfo.getString("description"));
            user = insertUser(user, 1);
        } catch (Exception e) {
            log.info("weiboyichang+++++++++++++++++++++++++++++++++++++++++++++++");
            e.printStackTrace();
        }
        return new Result(user);
    }

    @Transactional
    public User insertUser(User user, int scope) {
        if(user.getUid()==null||user.getUid()==""){
            user.setUid(UUID.randomUUID().toString());
        }
        if (userMapper.selectBySourceAndUid(scope, user.getUid()) == null) {
            user.setProfileUrl("http://zx-blog.oss-cn-beijing.aliyuncs.com/Carousel/download.jpg");
            user.setGender("n");
            user.setuSource(0);
            insert(user);
        }
        user = userMapper.selectBySourceAndUid(scope, user.getUid());
        return user;
    }

    public Result cheackName(String name, int id) {

        return new Result();
    }

    public void insert(User user) {
        user.setCreatedTime(System.currentTimeMillis());
        userMapper.insert(user);
    }


    public Result qqLogin(String code) {
        User user1 = userMapper.selectById(48);
        if (user1 != null) {
            return new Result(user1);
        }
        log.info("qqlogin statrt---------------------------------------");
        User user = null;
        try {
            String access_token = "";
            String openId = "";
            String url = QQGET_TOKEN_URL + "?grant_type=authorization_code&client_id=" + QQCLIENT_ID + "&client_secret=" + QQCLIENT_SERCRET + "&code=" + code + "&redirect_uri=" + QQREDIRECT_URI;
            String token = HttpUtils.getString(url);
            log.info("qqlogin token" + token + "---------------------------------------");
            if (token.indexOf("access_token") == -1) {
                return new Result(400, "登录失败");
            }
            access_token = token.substring(token.indexOf("=") + 1, token.indexOf("&"));
            String oid = HttpUtils.getString(QQGET_OPEN_ID + "?access_token=" + access_token);
            openId = oid.substring(oid.indexOf("openid\":\"") + 9, oid.lastIndexOf("\""));
            url = QQGET_USER_INFO + "?access_token=" + access_token + "&oauth_consumer_key=" + QQCLIENT_ID + "&openid=" + openId;
            JSONObject userInfo = HttpUtils.get(url);
            log.info("qqlogin userInfo" + userInfo.toString() + "---------------------------------------");
            String gender;
            if ("男".equals(userInfo.getString("gender"))) {
                gender = "m";
            } else {
                gender = "f";
            }
            user = new User(openId, userInfo.getString("nickname"), 2, "未知", userInfo.getString("figureurl_qq_1"), gender, "");
            user = insertUser(user, 2);
        } catch (Exception e) {
            log.info("qqechang+++++++++++++++++++++++++++++++++++++++++++++++");
            log.info(e.getMessage());
            e.printStackTrace();
        }
        return new Result(user);
    }

    public Result getUserById(Integer id) {
        return new Result(userMapper.selectById(id));
    }

    public Result login(User user) {
        List<User> userList = userMapper.selectStatusByName(user.getUserName());
        for (User user1 : userList) {
            if (user1 != null && user1.getStatus() == 0 && user.getPassWord().equalsIgnoreCase(user1.getPassWord())) {
                user1= getUserClick(user1);
                return new Result(user1);
            }
        }
        return new Result(400,"登录信息有误");
    }

    public Result updateUser(User user) {
        userMapper.updateUser(user);
        return new Result();
    }
    public User getUserClick(User user) {
        user.setBlogclick(userMapper.selectUserBlogClick(user.getId()));
        user.setResouceClick(userMapper.selectUserResourceClick(user.getId()));
        return user;
    }
}
