package com.centling.mapper.blog;

import com.centling.domain.Comments;
import com.centling.domain.Resouce;

import java.util.List;
import java.util.Map;

public interface ResouceMapper {
     void insert(Resouce resouce);
     List<Resouce> selectPage(Map map);
}
