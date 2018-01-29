package com.centling.controller.blog;

import com.centling.domain.Comments;
import com.centling.domain.Resouce;
import com.centling.service.ResouceService;
import com.centling.utils.GridPageRequest;
import com.centling.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {
    @Autowired
    ResouceService resouceService;
    @PostMapping(value = "/getResouceList/{size}")
    public Result getResouceList(@RequestBody Resouce comments, @PathVariable Integer size){
        return resouceService.getResouceList(comments,size);
    }
    @PostMapping(value = "/resourceClick/{ip}")
    public Result resourceClick(@RequestBody Integer id, @PathVariable String ip){
        return resouceService.resourceClick(id,ip);
    }
    @PostMapping(value = "/updateRes")
    public Result updateRes(@RequestBody Resouce comments){
        return resouceService.updateRes(comments);
    }
    @PostMapping(value = "/getResouceList")
    public Result getResouceList(@RequestBody GridPageRequest gridPageRequest){
        return resouceService.getResouceList(gridPageRequest);
    }
    @RequestMapping(value = "/saveResouce",method= RequestMethod.POST)
    public Result saveResouce(@RequestBody Resouce comments){
        return resouceService.saveResouce(comments);
    }
    @PostMapping(value = "/passResourc/{id}")
    public Result passResourc(@RequestBody Integer status,@PathVariable String id){
        return resouceService.passResourc(status,id);
    }
    @PostMapping(value = "/deleteRe/{id}")
    public Result deleteRe(@PathVariable Integer id){
        return resouceService.deleteRe(id);
    }
}
