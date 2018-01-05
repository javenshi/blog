package com.centling.mapper.blog;

import com.centling.domain.Notice;
import com.centling.domain.Resouce;

import java.util.List;
import java.util.Map;

public interface NoticeMapper {
    void insert(Notice notice);
    List<Notice> selectPage();
}
