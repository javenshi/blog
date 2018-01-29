package com.centling.controller.blog;

import com.centling.domain.Proposal;
import com.centling.domain.Resouce;
import com.centling.mapper.blog.RankIngMapper;
import com.centling.service.ProposalService;
import com.centling.service.ResouceService;
import com.centling.utils.GridFilterInfo;
import com.centling.utils.GridPageRequest;
import com.centling.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proposal")
public class ProposalController {
    @Autowired
    ProposalService proposalService;
    @Autowired
    RankIngMapper rankIngMapper;

    @PostMapping(value = "/getProposalList")
    public Result getResouceList( @RequestBody GridPageRequest gridPageRequest){
        return proposalService.getProposalList(gridPageRequest);
    }
    @PostMapping(value = "/update")
    public Result updateRes(@RequestBody Proposal comments){
        return proposalService.updateProposal(comments);
    }

    @RequestMapping(value = "/insert",method= RequestMethod.POST)
    public Result saveResouce(@RequestBody Proposal comments){
        return proposalService.saveProposal(comments);
    }


    @PostMapping(value = "/delete/{id}")
    public Result deleteRe(@PathVariable Integer id){

        return proposalService.deleteRe(id);
    }
    @PostMapping(value = "/getRankIng")
    public Result getRankIng(){

        return new Result(rankIngMapper.selectPage());
    }
}
