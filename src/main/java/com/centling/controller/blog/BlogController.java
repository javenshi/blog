package com.centling.controller.blog;

import com.aliyun.oss.OSSClient;
import com.centling.domain.Blogs;
import com.centling.service.BlogsService;
import com.centling.utils.GridPageRequest;
import com.centling.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    @Autowired
    BlogsService blogsService;

    @RequestMapping(value = "/insert",method= RequestMethod.POST)
    public Result insert(@RequestBody Blogs blogs){
        return blogsService.insert(blogs);
    }
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest){
        return new Result(blogsService.selectPage(gridPageRequest));
    }
    @PostMapping(value = "/getBlogsById")
    public Result getBlogsById(@RequestBody String id){
        return blogsService.getBlogsById(id);
    }
    @PostMapping(value = "/passBlog/{id}")
    public Result passBlog(@RequestBody Integer status,@PathVariable String id){
        return blogsService.passBlog(status,id);
    }
}
