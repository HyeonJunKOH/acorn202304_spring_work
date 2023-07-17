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
			.addPathPatterns("/users/*","/cafe/*","/file/*","/gallery/*")
			.excludePathPatterns("/users/loginform","/users/login","/users/signup_form","/users/signup")
			.excludePathPatterns("/cafe/list","/cafe/detail","/cafe/ajax_comment_list","/file/list","/file/download"
								,"/gallery/list","/gallery/detail","/gallery/images/*");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}
