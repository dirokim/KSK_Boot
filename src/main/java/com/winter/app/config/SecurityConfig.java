package com.winter.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web
				.ignoring()
				.requestMatchers("/css/**")
				.requestMatchers("/js/**")
				.requestMatchers("/vendor/**")
				.requestMatchers("/img/**")
				.requestMatchers("/favicon/**");
	}
	@Bean	
	SecurityFilterChain filterChain (HttpSecurity security)throws Exception {
		security.authorizeHttpRequests((authorizeRequests)->
		authorizeRequests
		.requestMatchers("/").permitAll()
		.requestMatchers("/member/add").permitAll()
		.requestMatchers("/notice/add","/notice/delete").hasRole("ADMIN")
		.requestMatchers("/notice/update").hasAnyRole("ADMIN","MANAGER")
		.anyRequest().permitAll()
		);
		return security.build();
	}
	
}
