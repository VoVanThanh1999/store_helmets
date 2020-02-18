package com.boss.storehelmets.springmvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
public class SpringMvcConfig {
	
	public void addResoucesHanders(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/css/**").addResourceLocations("classpath:/static/css/");
	}
	
}
