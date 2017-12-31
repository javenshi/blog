package com.centling.service;

import com.centling.config.ApplicationProperties;
import com.centling.domain.User;
import com.centling.mapper.blog.UserMapper;
import com.centling.security.SecurityUtils;
import com.centling.security.jwt.TokenProvider;
import com.centling.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserMapper userMapper;
    public Result cheackName(String name,int id){
        if(userMapper.selectByNameAndId(name,id)>0){
            return new Result(202,"用户已存在");
        }
        return new Result();
    }

    public void insert(User user) {
        user.setCreatedTime(System.currentTimeMillis());
        userMapper.insert(user);
    }

    public Result login(User user) {
        if( userMapper.selectByNameAndId(user.getUserName(), 0)==0){
            return new Result(202,"用户不存在");
        }else if(userMapper.selectByNameAndPassWord(user)>0){
            User u=userMapper.selectStatusByName(user.getUserName());
            if(u.getStatus()==0){
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassWord());

                try {
                   Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    tokenProvider.createToken(authentication, true);
                } catch (AuthenticationException exception) {
                    exception.printStackTrace();
                }
                return new Result(200,"登录成功",u);
            }else{
                return new Result(202,"用户不可用");
            }
        }else{
            return new Result(202,"密码错误");
        }
    }
}
