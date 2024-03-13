package com.winter.app.config;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.winter.app.member.MemberService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private SecurityLoginSucessHandler handler;
	@Autowired
	private SecurityLoginFailHandler failHandler;
	@Autowired
	private MemberService memberService;
	
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
				.requestMatchers("/member/page").authenticated()
				.requestMatchers("/notice/list").authenticated()
				.requestMatchers("/notice/add","/notice/delete").hasRole("ADMIN")
				.requestMatchers("/notice/update").hasAnyRole("ADMIN","MANAGER")
				.anyRequest().permitAll()
		)//authorizeHttpRequests  끝
		.formLogin((login)->login
				.loginPage("/member/login") //url 정보
//			.defaultSuccessUrl("/")      //로그인 성공하면 어디로가나
//			.failureUrl("notice/list")
				.failureHandler(failHandler)
				.successHandler(handler)
				.permitAll()
		
		)//formlogin 끝;
		//로그아웃 
		.logout((logout)->logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
				.logoutSuccessUrl("/")
//				.logoutSuccessHandler(null)
				.invalidateHttpSession(true)	//로그아웃시 세션만료
				.permitAll()
				)
		//rememberMe
		.rememberMe((rememberMe)->
				rememberMe.rememberMeParameter("rememberMe")
				.tokenValiditySeconds(600)
				.key("rememberMe")
				.userDetailsService(memberService)
				.authenticationSuccessHandler(handler)
				.useSecureCookie(false)
				)
		//동시접속 처리
		.sessionManagement((sessionManagement)->sessionManagement
				.maximumSessions(1)  //최대 사용자
				.maxSessionsPreventsLogin(false)
				.expiredUrl("/expired")
				)
		//소셜 로그인
		.oauth2Login(
				(oauth2Login)->oauth2Login
				.userInfoEndpoint(
						(ue)->ue.userService(memberService))
				
				)
		;
				
		return security.build();
	}

	
}
