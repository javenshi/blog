package com.centling.mapper.blog;

import com.centling.domain.Notice;
import com.centling.domain.Proposal;

import java.util.List;

public interface ProposalMapper {
    void insert(Proposal notice);
    void delete(Integer id);
    void update(Proposal notice);
    List<Proposal> selectPage();
}
