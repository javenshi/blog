package com.centling.controller.blog;

import com.centling.domain.Blogs;
import com.centling.domain.Comments;
import com.centling.mapper.blog.BlogsMapper;
import com.centling.service.BlogsService;
import com.centling.utils.GridPageRequest;
import com.centling.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    @Autowired
    BlogsService blogsService;
    @Autowired
    BlogsMapper blogsMapper;

    @RequestMapping(value = "/insert",method= RequestMethod.POST)
    public Result insert(@RequestBody Blogs blogs){
        return blogsService.insert(blogs);
    }
    @RequestMapping(value = "/saveComents",method= RequestMethod.POST)
    public Result saveComents(@RequestBody Comments comments){
        return blogsService.saveComents(comments);
    }
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest){
        return new Result(blogsService.selectPage(gridPageRequest));
    }
    @PostMapping(value = "/getBlogsById/{ip}")
    public Result getBlogsById(@RequestBody String id,@PathVariable String ip){
        return blogsService.getBlogsById(id,ip);
    }
    @PostMapping(value = "/passBlog/{id}")
    public Result passBlog(@RequestBody Integer status,@PathVariable String id){
        return blogsService.passBlog(status,id);
    }
    @PostMapping(value = "/deleteBlog/{id}")
    public Result deleteBlog(@PathVariable String id){
        return blogsService.deleteBlog(id);
    }
    @PostMapping(value = "/getTopAndDown/{id}")
    public Result getTopAndDown(@PathVariable String id){
        return blogsService.getTopAndDown(id);
    }
    @PostMapping(value = "/getComentsList/{size}")
    public Result getComentsList(@RequestBody Comments comments,@PathVariable Integer size){
        return blogsService.getComentsList(comments,size);
    }
    @PostMapping(value = "/seletcClassifyName")
    public Result seletcClassifyName(){
        return new Result(blogsMapper.seletcClassifyName());
    }
    @PostMapping(value = "/insertClassName")
    public Result insertClassName(@RequestBody String className){
        blogsMapper.insertClassName(className);
        return new Result();
    }
}
