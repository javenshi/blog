package com.centling.service;

import com.centling.domain.Blogs;
import com.centling.domain.Comments;
import com.centling.domain.Resouce;
import com.centling.domain.User;
import com.centling.mapper.blog.BlogsMapper;
import com.centling.mapper.blog.CommentsMapper;
import com.centling.mapper.blog.ResouceMapper;
import com.centling.mapper.blog.UserMapper;
import com.centling.redis.RedisClient;
import com.centling.utils.GridPageRequest;
import com.centling.utils.GridReturnData;
import com.centling.utils.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ResouceService {
    @Autowired
    ResouceMapper resouceMapper;



    public Result saveResouce(Resouce resouce) {
        resouce.setCreatTime(System.currentTimeMillis());
        resouceMapper.insert(resouce);
        return new Result();
    }

    public Result getResouceList(Resouce resouce, Integer seze) {
        Map map=new HashMap();
        if(resouce.getResouceName()!=null)
        map.put("resouceName",resouce.getResouceName());
        if(resouce.getStatus()<3)
        map.put("status",resouce.getStatus());
        PageHelper.startPage(1, seze, "");
        List<Resouce> list= resouceMapper.selectPage(map);
        PageInfo<Resouce> pageInfo = new PageInfo<>(list);
      return new Result(pageInfo);
    }

    public Result passResourc(Integer status, String id) {
        resouceMapper.passResourc(status,id);
        return new Result();
    }
}
