package com.winter.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web		//제외 설정
				.ignoring()
				.requestMatchers("/css/**")
				.requestMatchers("/js/**")
				.requestMatchers("/vendor/**")
				.requestMatchers("/img/**")
				.requestMatchers("/favicon/**");
	}
	@Bean	
	SecurityFilterChain filterChain (HttpSecurity security)throws Exception {
		security
		//권한 설정
		.authorizeHttpRequests((authorizeRequests)->authorizeRequests
		.requestMatchers("/").permitAll()
		.requestMatchers("/member/add").permitAll()
		.requestMatchers("/notice/list").authenticated()
		.requestMatchers("/notice/add","/notice/delete").hasRole("ADMIN")
		.requestMatchers("/notice/update").hasAnyRole("ADMIN","MANAGER")
		.anyRequest().permitAll()
		)//authorizeHttpRequests  끝
		.formLogin((login)->login
		.loginPage("/member/login") //url 정보
		.defaultSuccessUrl("/")      //로그인 성공하면 어디로가나
		.permitAll()
		
		)//formlogin 끝;
		.logout(
				(logout)->logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
				.logoutSuccessUrl("/member/login")
				.invalidateHttpSession(true)	//로그아웃시 세션만료
				.permitAll()
				);
				
		return security.build();
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		//password 암호화 해주는 객체
		return new BCryptPasswordEncoder();
	}
	
}
