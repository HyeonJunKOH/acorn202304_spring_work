package com.example.boot07.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.boot07.interceptor.LoginInterceptor;


@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	LoginInterceptor loginInter;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInter)
			.addPathPatterns("/users/*")
			.excludePathPatterns("/users/loginform")
			.excludePathPatterns("/users/login");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}
