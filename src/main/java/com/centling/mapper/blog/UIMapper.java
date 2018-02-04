package com.centling.mapper.blog;

import com.centling.domain.UI;

import java.util.List;

public interface UIMapper {
    void insert(UI ui);
    void addClick(Integer id);
    UI selectById(Integer id);
    List<UI> selectPage();
}
