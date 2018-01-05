package com.centling.controller.blog;

import com.centling.domain.Proposal;
import com.centling.domain.Resouce;
import com.centling.service.ProposalService;
import com.centling.service.ResouceService;
import com.centling.utils.GridPageRequest;
import com.centling.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proposal")
public class ProposalController {
    @Autowired
    ProposalService proposalService;
    @PostMapping(value = "/getProposalList/{size}")
    public Result getResouceList( @PathVariable Integer size){
        return proposalService.getProposalList(size);
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
}
