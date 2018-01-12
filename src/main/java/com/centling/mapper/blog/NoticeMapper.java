package com.centling.mapper.blog;

import com.centling.domain.Notice;

import java.util.List;

public interface NoticeMapper {
    void insert(Notice notice);
    List<Notice> selectPage();
}
