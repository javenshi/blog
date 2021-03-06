package com.centling.mapper.blog;

import com.centling.domain.Blogs;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BlogsMapper {
    void insert(Blogs blogs);



    List<Blogs> selectPage(Map map);

    Blogs getBlogsById(String id);

    void addClick( String id);

    Integer selectClassId(String id);

    void passBlog(@Param("status") Integer status, @Param("id") String id);

    void updateBlogs(Blogs blogs);

    void deleteBlog(@Param("deleteTime") long time,@Param("id") String id);

    void addClassClick(Integer classId);

    List<String> seletcClassifyName();

    Blogs selectTopBlogId(String id);
    Blogs selectDownBlogId(String id);

    int selectClassByName(String className);

    void insertClassName(String className);


}
