package com.centling.service;

import com.centling.domain.Proposal;
import com.centling.domain.Resouce;
import com.centling.mapper.blog.ProposalMapper;
import com.centling.mapper.blog.ResouceMapper;
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



    public Result saveProposal(Proposal resouce) {
        resouce.setCreatTime(System.currentTimeMillis());
        proposalMapper.insert(resouce);
        return new Result();
    }

    public Result getProposalList(Integer seze) {
        PageHelper.startPage(1, seze, "");
        List<Proposal> list= proposalMapper.selectPage();
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
