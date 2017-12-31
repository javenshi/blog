package com.centling.controller;

import com.aliyun.oss.OSSClient;
import com.centling.mapper.blog.admin.CarouselMapper;
import com.centling.utils.OSSUtil;
import com.centling.utils.Result;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    CarouselMapper carouselMapper;
    @Autowired
    OSSUtil ossUtil;


    @PostMapping(value = "/getAllCarousel")
    public Result getAllCarousel(){
        return new Result(carouselMapper.selectAll());
    }
    @RequestMapping(value = "/upl",method= RequestMethod.POST,produces= MediaType.APPLICATION_JSON_VALUE)
    public Result upl(@RequestParam(value="file",required=false)MultipartFile file){
        OSSClient ossClient= ossUtil.getUploadOSSClient();
        String key=ossUtil.uploadToOSS(ossClient,"Carousel/",file);
        String url="<img src=\""+key+"\">";
        carouselMapper.insert(url);
        return new Result();
    }
    @DeleteMapping(value = "/deleteCarousel/{id}")
    public Result deleteCarousel(@PathVariable Integer id){
        carouselMapper.deleteById(id);
        return new Result();
    }
}
