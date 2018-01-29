package com.centling.service;

import com.centling.domain.*;
import com.centling.mapper.blog.BlogsMapper;
import com.centling.mapper.blog.CommentsMapper;
import com.centling.mapper.blog.RankIngMapper;
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
    @Autowired
    RankIngMapper rankIngMapper;


    public Result insert(Blogs blogs) {
        String ClassifyName = blogsMapper.seletcClassifyById(blogs.getBlogsClassifyId());
        blogs.setBlogsClassifyName(ClassifyName);
        User u = userMapper.selectStatusByName(blogs.getUserName());
        if (blogs.getId() != null) {
            blogs.setBlogsDate(System.currentTimeMillis());
            blogsMapper.updateBlogs(blogs);
        } else {
            String uuid = UUID.randomUUID().toString();
            blogs.setBlogs(uuid, u);
            blogsMapper.insert(blogs);
        }
        return new Result();
    }

    public GridReturnData<Blogs> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<Blogs> mGridReturnData = new GridReturnData<>();
        Map map = gridPageRequest.getFilterList();
        map.put("searchKey", gridPageRequest.getSearchKey());
        String sortMyBatisByString = gridPageRequest.getSortMybatisString();
        PageHelper.startPage(gridPageRequest.getPageNum(), gridPageRequest.getPageSize(), sortMyBatisByString);
        List<Blogs> list = blogsMapper.selectPage(map);
        PageInfo<Blogs> pageInfo = new PageInfo<>(list);
        mGridReturnData.setPageInfo(pageInfo);
        return mGridReturnData;
    }

    public Result getBlogsById(String id, String ip) {
        Blogs blogs = blogsMapper.getBlogsById(id);
        if (blogs != null) {
            if (redisClient.getBlogsClick(id, ip) == null) {
                redisClient.setBlogsClick(id, ip);
                blogsMapper.addClick(id);
                blogs.setBlogsClick(blogs.getBlogsClick() + 1);
                Integer classId = blogsMapper.selectClassId(id);
                blogsMapper.addClassClick(classId);
                if (rankIngMapper.selectById(id.toString()) == null) {
                    rankIngMapper.insert(new RankIng(id,blogs.getBlogsName()));
                }
                rankIngMapper.addClick(id);
            }
            return new Result(blogs);
        }
        return new Result(404, "");
    }

    public Result passBlog(Integer status, String id) {
        blogsMapper.passBlog(status, id);
        return new Result();
    }

    public Result saveComents(Comments comments) {
        comments.setCreatTime(System.currentTimeMillis());
        commentsMapper.insert(comments);
        return new Result();
    }

    public Result getComentsList(Comments comments, Integer seze) {
        Map map = new HashMap();
        map.put("blogId", comments.getBlogId());
        PageHelper.startPage(1, seze, "");
        List<Comments> list = commentsMapper.selectPage(map);
        PageInfo<Comments> pageInfo = new PageInfo<>(list);
        return new Result(pageInfo);
    }

    public Result deleteBlog(String id) {
        blogsMapper.deleteBlog(System.currentTimeMillis(), id);
        return new Result();
    }

    public Result getTopAndDown(String id) {
        List<Blogs> list = new ArrayList<>();
        Blogs top = blogsMapper.selectTopBlogId(id);
        Blogs down = blogsMapper.selectDownBlogId(id);

        list.add(top);
        list.add(down);

        return new Result(list);
    }
}
