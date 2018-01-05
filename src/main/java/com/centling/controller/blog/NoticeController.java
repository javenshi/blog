package com.centling.controller.blog;

import com.centling.domain.Notice;
import com.centling.mapper.blog.NoticeMapper;
import com.centling.utils.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {
    @Autowired
    NoticeMapper noticeMapper;
    @PostMapping(value = "/getNoticeList")
    public Result getNoticeList(){
        PageHelper.startPage(1, 5,"");
        List<Notice> list= noticeMapper.selectPage();
        PageInfo<Notice> pageInfo = new PageInfo<>(list);
        return new Result(pageInfo);
    }
    @RequestMapping(value = "/saveNotice",method= RequestMethod.POST)
    public Result saveNotice(@RequestBody Notice comments){
        comments.setCreatTime(System.currentTimeMillis());
        noticeMapper.insert(comments );
        return new Result();
    }

}
