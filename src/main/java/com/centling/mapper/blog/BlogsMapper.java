package com.centling.mapper.blog;

import com.centling.domain.Blogs;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BlogsMapper {
    void insert(Blogs blogs);

    String seletcClassifyById(Integer id);

    List<Blogs> selectPage(Map map);

    Blogs getBlogsById(String id);

    void addClick(String id);

    void passBlog(@Param("status") Integer status, @Param("id") String id);

    void updateBlogs(Blogs blogs);
}
