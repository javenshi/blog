/*
package com.centling.config;

import com.centling.mapper.blog.admin.CarouselMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Task {
    @Autowired
    CarouselMapper carouselMapper;
    @Scheduled(cron = "0 1/1 * * * ? ")
    public void excuteTimeOut() {
        carouselMapper.selectTimeOut();
    }

}
*/
