package com.centling.service;

import com.centling.domain.Blogs;
import com.centling.domain.Proposal;
import com.centling.domain.Resouce;
import com.centling.mapper.blog.ProposalMapper;
import com.centling.mapper.blog.ResouceMapper;
import com.centling.mapper.blog.UserMapper;
import com.centling.utils.GridFilterInfo;
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
public class ProposalService {
    @Autowired
    ProposalMapper proposalMapper;
    @Autowired
    UserMapper userMapper;



    public Result saveProposal(Proposal resouce) {
        if(resouce.getPid()!=0){
            Proposal proposal=  proposalMapper.selectById(resouce.getPid());
            proposalMapper.addPro(proposal.getId());
            resouce.setToUserName(proposal.getUserName());
            resouce.setToUserId(proposal.getUserId());
        }
        resouce.setCreatTime(System.currentTimeMillis());
        proposalMapper.insert(resouce);
        return new Result();
    }

    public Result getProposalList(GridPageRequest gridPageRequest) {
        Map map= gridPageRequest.getFilterList();
        map.put("searchKey", gridPageRequest.getSearchKey());
        PageHelper.startPage(1, gridPageRequest.getPageSize(), "");
        List<Proposal> list= proposalMapper.selectPage(map);
        for( int i=0;i<list.size();i++){
            if(list.get(i).getHasChild()==1){
             List<Proposal> l=   proposalMapper.selectByPId(list.get(i).getId());
                list.get(i).setChildren(l);
            }
        }
        PageInfo<Proposal> pageInfo = new PageInfo<>(list);
      return new Result(pageInfo);
    }




    public Result updateProposal(Proposal comments) {

        comments.setCreatTime(System.currentTimeMillis());
        proposalMapper.update(comments);

        return new Result();
    }

    public Result deleteRe(Integer id) {
        proposalMapper.delete(id);
        return new Result();
    }
}
