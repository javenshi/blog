package com.centling.mapper.blog;

import com.centling.domain.RankIng;

import java.util.List;

public interface RankIngMapper {
     void insert(RankIng rankIng);
    RankIng selectById(String id);

    void addClick(String id);

    List<RankIng>  selectPage();
}
