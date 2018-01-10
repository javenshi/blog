package com.centling.service;

import com.centling.domain.Blogs;
import com.centling.domain.Comments;
import com.centling.domain.User;
import com.centling.mapper.blog.BlogsMapper;
import com.centling.mapper.blog.CommentsMapper;
import com.centling.mapper.blog.UserMapper;

import com.centling.redis.RedisClient;
import com.centling.utils.GridPageRequest;
import com.centling.utils.GridReturnData;
import com.centling.utils.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlogsService {
    @Autowired
    BlogsMapper blogsMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    CommentsMapper commentsMapper;
    @Autowired
    RedisClient redisClient;

    public Result insert(Blogs blogs){
       String ClassifyName= blogsMapper.seletcClassifyById(blogs.getBlogsClassifyId());
        blogs.setBlogsClassifyName(ClassifyName);
        User u=userMapper.selectStatusByName(blogs.getUserName());
        if(blogs.getId()!=null){
            redisClient.setStringKey(blogs.getId(),blogs.getBlogsUrl());
            blogs.setBlogsUrl("");
            blogs.setBlogsDate(System.currentTimeMillis());
            blogsMapper.updateBlogs(blogs);
        }else{
            String uuid= UUID.randomUUID().toString();
            redisClient.setStringKey(uuid,blogs.getBlogsUrl());
            blogs.setBlogs(uuid,u);
            blogsMapper.insert(blogs);
        }
        return new Result();
    }
    public GridReturnData<Blogs> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<Blogs> mGridReturnData = new GridReturnData<>();
        Map map= gridPageRequest.getFilterList();
        map.put("searchKey", gridPageRequest.getSearchKey());
        String sortMyBatisByString = gridPageRequest.getSortMybatisString();
        PageHelper.startPage(gridPageRequest.getPageNum(), gridPageRequest.getPageSize(), sortMyBatisByString);
        List<Blogs> list = blogsMapper.selectPage(map);
        PageInfo<Blogs> pageInfo = new PageInfo<>(list);
        mGridReturnData.setPageInfo(pageInfo);
        return mGridReturnData;
    }

    public Result getBlogsById(String id) {
        Blogs blogs=blogsMapper.getBlogsById(id);
        if(blogs!=null){
            blogs.setBlogsUrl(redisClient.getStringKey(id));
            blogsMapper.addClick(id);
            redisClient.setZKey("<a href='/blog/read?id='"+id+">"+blogs.getUserName()+"</a>");
            return new Result(blogs);
        }
        return new Result(404,"");
    }

    public Result passBlog(Integer status, String id) {
        blogsMapper.passBlog(status, id);
        return  new Result();
    }

    public Result saveComents(Comments comments) {
        comments.setCreatTime(System.currentTimeMillis());
        commentsMapper.insert(comments);
        return new Result();
    }

    public Result getComentsList(Comments comments, Integer seze) {
        Map map=new HashMap();
        map.put("blogId",comments.getBlogId());
        PageHelper.startPage(1, seze, "");
        List<Comments> list= commentsMapper.selectPage(map);
        PageInfo<Comments> pageInfo = new PageInfo<>(list);
      return new Result(pageInfo);
    }

    public Result deleteBlog(String id) {
        blogsMapper.deleteBlog(System.currentTimeMillis(),id);
        return new Result();
    }
}
