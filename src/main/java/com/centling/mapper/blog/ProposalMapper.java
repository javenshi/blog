package com.centling.mapper.blog;

import com.centling.domain.Notice;
import com.centling.domain.Proposal;

import java.util.List;
import java.util.Map;

public interface ProposalMapper {
    void insert(Proposal notice);
    void delete(Integer id);
    void update(Proposal notice);
    List<Proposal> selectPage(Map map);
    Proposal selectById(int id);

    void addPro(int id);

    List<Proposal> selectByPId(int id);


}
