package com.centling.mapper.blog;

import com.centling.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int selectByNameAndId(@Param("name") String name,@Param("id") int id);
    int insert(User u);

    int selectByNameAndPassWord(User user);

    User selectStatusByName(String userName);
}
