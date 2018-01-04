package com.centling.controller.blog;

import com.centling.domain.Comments;
import com.centling.domain.Resouce;
import com.centling.service.ResouceService;
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
    @RequestMapping(value = "/saveResouce",method= RequestMethod.POST)
    public Result saveResouce(@RequestBody Resouce comments){
        return resouceService.saveResouce(comments);
    }
}
