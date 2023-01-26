package com.situ.taskmgr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.situ.taskmgr.interceptor.UserInterceptor;

@Configuration //spring容器 作为一个配置类的对象
public class WebConfig implements WebMvcConfigurer{
	
	@Autowired
	private UserInterceptor userInterceptor;
	
	/**
	 * 注册拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		registry.addInterceptor(userInterceptor)
		.addPathPatterns("/**") //指定拦截路径 /**拦截所有路径
		.excludePathPatterns("/user/login",
				"/img/*",
				"/layui/**"
				); //排除不需要拦截的路径
	}
}
