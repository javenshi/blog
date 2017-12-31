package com.centling.mapper.blog.admin;

import com.centling.domain.Carousel;

import java.util.List;

public interface CarouselMapper {
    List<Carousel> selectAll();

    void insert(String url);

    void deleteById(Integer id);
}
