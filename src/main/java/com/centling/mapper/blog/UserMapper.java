package com.centling.mapper.blog;

import com.centling.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User selectBySourceAndUid(@Param("sorce") int sorce,@Param("uid") String id);
    int insert(User u);

    int selectByNameAndPassWord(User user);

   List<User> selectStatusByName(String userName);
    User selectById(int id);

    User selectUserByNameAndUid(@Param("name") String name,@Param("uid") String id);
}
