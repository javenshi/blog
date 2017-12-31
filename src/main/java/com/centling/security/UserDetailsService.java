
package com.centling.security;


import com.centling.mapper.blog.UserMapper;
import com.centling.utils.exception.ApplicationErrorEnum;
import com.centling.utils.exception.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;


/**
 * Authenticate a user from the database.
 */

@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    UserMapper userMapper;


    public UserDetails loadUserByUsername(final String name) {

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        TokenUser tokenUser = new TokenUser(name, userMapper.selectStatusByName(name).getPassWord(), grantedAuthorities);

        return tokenUser;
    }
/*
  if(log.isDebugEnabled()){
            log.debug("Authenticating:[用户名:{}]", name);
        }
        String lowercaseName = name;//.toLowerCase();
        //XXX:状态码 0,1 放到静态类中去
        SUser user=sUserMapper.selectByUserName(lowercaseName);
        Optional<SUser> userFromDatabase = Optional.ofNullable(sUserMapper.findOneByUserNameAndStatus(lowercaseName,"1"));
       // return userFromDatabase.map(user -> {
            //XXX:状态码 0,1 放到静态类中去
            if ("0".equals(user.getStatus())) {
                throw new UserNotActivatedException("User " + lowercaseName + " was not activated");
            }
            //TODO: 特殊角色(不需要授权即可拥有全部权限的角色,会造成硬编码，需要注意)
            Set<String> userAuthorities = this.getAuthoritiesByUserId(user.getId());

            List<GrantedAuthority> grantedAuthorities  = userAuthorities.stream().map(authorityName -> new SimpleGrantedAuthority(authorityName))
                    .collect(toList());
            //XXX:添加默认角色，防止出现未设置任何角色情况下,出现A granted authority textual representation is required错误
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_DEFAULT"));
//            user.setAuthorities(null);
            if(log.isDebugEnabled()){
                log.debug("用户授权信息加载...:[用户名:{},用户密码:{},用户角色:{}]",lowercaseName,"*****",grantedAuthorities);
            }

            UserInfoExt userInfoExt = new UserInfoExt();
            userInfoExt.setUserId(user.getId());
            List<UserInfoExt> userInfoExtList = userInfoExtMapper.selectSelective(userInfoExt);
            Preconditions.checkArgument(userInfoExtList!=null && userInfoExtList.size()==1, ApplicationErrorEnum.USER_NOT_FOUND);
            user.setUserInfo(userInfoExtList.get(0));

            TokenUser tokenUser = new TokenUser(lowercaseName, user.getPassword(), grantedAuthorities);
            tokenUser.setUserId(user.getId());
            tokenUser.setUser(user);
            return tokenUser;
       // }).orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseName
                               // + " was not found in the database"));/*

    }*/

    /* private Set<String> getAuthoritiesByUserId(Integer id){

     *//*   SUser sUser = this.getCurrentUserByUserId(id);
        Preconditions.checkArgument(!Strings.isNullOrEmpty(sUser.getRoleIds()), ApplicationErrorEnum.USER_NOT_HAVE_ANY_ROLES);
        List<Integer> roleValidIds = this.getValidRoleIds(sUser.getRoleIds());
        Preconditions.checkArgument(roleValidIds.size() > 0,ApplicationErrorEnum.USER_NOT_HAVE_ANY_VALID_ROLES);

        List<RoleAuthority> roleAuthorities = roleAuthorityMapper.selectByRoleIds(roleValidIds);
        Preconditions.checkArgument(roleAuthorities.size() > 0,ApplicationErrorEnum.USER_NOT_HAVE_ANY_AUTHORITY);
        List<Integer> authorityIds = roleAuthorities
                .stream()
                .map(RoleAuthority::getAuthorityId)
                .collect(toList());

        List<Authority> authorities = authorityMapper.selectByIds(authorityIds);
        Preconditions.checkArgument(authorities.size() > 0,ApplicationErrorEnum.USER_NOT_HAVE_ANY_AUTHORITY);
        return authorities
                .stream()
                .map(Authority::getName)
                .collect(toSet());*//**//*

    }

   *//*
     *//* private List<Integer> getValidRoleIds(String roleIdStr){
     *//**//*
     *//*
     *//*   List<Integer> roleIds = Splitter
                .on(",")
                .trimResults()
                .omitEmptyStrings()
                .splitToList(roleIdStr)
                .stream().map(s -> Integer.valueOf(s))
                .collect(toList());
        Preconditions.checkArgument(roleIds.size() > 0, ApplicationErrorEnum.USER_NOT_HAVE_ANY_ROLES);
        //查询所有有效的角色
        Role roleCondition = new Role();
        roleCondition.setDeleteFlag(false);
        List<Role> roles = roleMapper.selectByObject(roleCondition);
        Preconditions.checkArgument(roles.size() > 0,ApplicationErrorEnum.USER_NOT_HAVE_ANY_ROLES);

        List<Integer> allVialdRoleIds = roles
                .stream()
                .map(Role::getId)
                .collect(toList());

        return roleIds
                .stream()
                .filter(value-> allVialdRoleIds.contains(value))
                .collect(toList());*//**//*
     *//*
     *//*
    }*//**//*


     *//*
     *//*  private SUser getCurrentUserByUserId(Integer userId){
        SUser sUser = sUserMapper.selectByPrimaryKey(userId);
        Preconditions.checkNotNull(sUser, ApplicationErrorEnum.USER_NOT_FOUND);
        return sUser;
    }*//**//*



     *//*
     */
}