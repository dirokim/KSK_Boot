package com.winter.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.winter.app.interceptor.TestInterceptor;

import ch.qos.logback.core.net.LoginAuthenticator;

@Configuration
public class InterceptorConfig implements  WebMvcConfigurer {
	

	@Autowired
	private LocaleChangeInterceptor  localeChangeInterceptor;

	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(testInterceptor)
//		.addPathPatterns("/notice/**")
//		.excludePathPatterns("/notice/add");
//		registry.addInterceptor(testInterceptor)
//		.addPathPatterns("/")
//		.excludePathPatterns("");
//		
        registry.addInterceptor(localeChangeInterceptor)
        .addPathPatterns("/**");
		
	}

	
	
	
}
