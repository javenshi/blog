package com.centling.controller.UI;

import com.aliyun.oss.OSSClient;
import com.centling.config.WebConfigurer;
import com.centling.domain.RankIng;
import com.centling.domain.Resouce;
import com.centling.domain.UI;
import com.centling.mapper.blog.UIMapper;
import com.centling.redis.RedisClient;
import com.centling.utils.OSSUtil;
import com.centling.utils.Result;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/ui")
public class UIController {
    @Autowired
    OSSUtil ossUtil;
    @Autowired
    UIMapper uiMapper;
    @Autowired
    RedisClient redisClient;
    private final org.slf4j.Logger log = LoggerFactory.getLogger(WebConfigurer.class);

    @RequestMapping(value = "/upl",method= RequestMethod.POST,produces= MediaType.APPLICATION_JSON_VALUE)
    public Result upl(@RequestParam(value="file",required=false)MultipartFile file){
        OSSClient ossClient= ossUtil.getUploadOSSClient();
        String key=ossUtil.uploadToOSS(ossClient,"ui/",file);
        return new Result(key);
    }
    @PostMapping(value = "/selectPage")
    public Result selectPage(){
        return new Result(uiMapper.selectPage());
    }
    @PostMapping(value = "/insert")
    public Result insert(@RequestBody UI ui){
        ui.setCreatedTime(System.currentTimeMillis());
        uiMapper.insert(ui);
        return new Result();
    }
    @PostMapping(value = "/getUIById/{ip}")
    public Result getUIById(@RequestBody Integer id, @PathVariable String ip){
        try {if (redisClient.getBlogsClick("uiid" + id, ip) == null) {
            redisClient.setBlogsClick("uiid" + id, ip);
            uiMapper.addClick(id);
        } } catch (Exception e) {
            e.printStackTrace();
            log.info("RedisExption------------------------------------------------------");
        }finally {
            return new Result(uiMapper.selectById(id));
        }

    }
}
