package com.centling.service;


import com.centling.domain.Blogs;
import com.centling.domain.RankIng;
import com.centling.domain.Resouce;
import com.centling.mapper.blog.RankIngMapper;
import com.centling.mapper.blog.ResouceMapper;

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

@Service
public class ResouceService {
    @Autowired
    ResouceMapper resouceMapper;
    @Autowired
    RedisClient redisClient;
    @Autowired
    RankIngMapper rankIngMapper;


    public Result saveResouce(Resouce resouce) {
        resouce.setCreatTime(System.currentTimeMillis());
        resouceMapper.insert(resouce);
        return new Result();
    }

    public Result getResouceList(Resouce resouce, Integer seze) {
        Map map = new HashMap();
        if (resouce.getResouceName() != null && resouce.getResouceName() != "")
            map.put("resouceName", resouce.getResouceName());
        if (resouce.getStatus() < 3)
            map.put("status", resouce.getStatus());
        PageHelper.startPage(seze, 10, "");
        List<Resouce> list = resouceMapper.selectPage(map);
        PageInfo<Resouce> pageInfo = new PageInfo<>(list);
        return new Result(pageInfo);
    }

    public Result getResouceList(GridPageRequest gridPageRequest) {
        GridReturnData<Resouce> mGridReturnData = new GridReturnData<>();
        Map map = gridPageRequest.getFilterList();
        map.put("searchKey", gridPageRequest.getSearchKey());
        PageHelper.startPage(gridPageRequest.getPageNum(), gridPageRequest.getPageSize(), "");
        List<Resouce> list = resouceMapper.selectPage(map);
        PageInfo<Resouce> pageInfo = new PageInfo<>(list);
        mGridReturnData.setPageInfo(pageInfo);
        return new Result(mGridReturnData);

    }

    public Result passResourc(Integer status, String id) {
        resouceMapper.passResourc(status, id);
        return new Result();
    }

    public Result updateRes(Resouce comments) {
        comments.setCreatTime(System.currentTimeMillis());
        resouceMapper.updateRes(comments);

        return new Result();
    }

    public Result deleteRe(Integer id) {
        resouceMapper.deleteRe(System.currentTimeMillis(), id);
        return new Result();
    }

    public Result getResoucesById(Integer id, String name) {

        resouceMapper.addClick(id);

        return new Result();


    }

    public Result resourceClick(Integer id, String ip) {

        if (redisClient.getBlogsClick("resourceid" + id, ip) == null) {
            redisClient.setBlogsClick("resourceid" + id, ip);
            resouceMapper.addClick(id);
            if (rankIngMapper.selectById(id.toString()) == null) {
                Resouce resouce= resouceMapper.selectById(id);
                rankIngMapper.insert(new RankIng(id.toString(),resouce.getResouceName(),resouce.getResouceUrl(),resouce.getResouceClick(),resouce.getUserName(),resouce.getContext(),resouce.getProfileUrl()));
            }
            rankIngMapper.addClick(id.toString());
        }
        return new Result();


    }


}
