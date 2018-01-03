package com.centling.mapper.blog;

import com.centling.domain.Comments;

import java.util.List;
import java.util.Map;

public interface CommentsMapper {
     void insert(Comments comments);
     List<Comments> selectPage(Map map);
}
