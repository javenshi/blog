package com.centling;

import com.centling.config.ApplicationProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableConfigurationProperties({ ApplicationProperties.class})
@EnableScheduling
@EnableAsync
public class Application {
	
	public static void main(String[] args) {
		
		ConfigurableApplicationContext app = SpringApplication.run(Application.class, args);
	}
	@Bean(value = "asyncTaskScheduler")
	public TaskExecutor taskExecutor() {
	    return new SimpleAsyncTaskExecutor(); // Or use another one of your liking
	}


}
