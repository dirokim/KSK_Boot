package com.winter.app.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class SecurityLoginSucessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String rememberId = request.getParameter("rememberId");
		if(rememberId !=null) {
			//꺼낸 아이디를 클라이언트 쿠키에 저장
			Cookie cookie = new Cookie("rememberId", authentication.getName());
			cookie.setMaxAge(600);	//최대 시간 초로 계산
			cookie.setPath("/"); //서브 도메인에서도 사용 가능
			response.addCookie(cookie); //내가만든 쿠키 ~
			
		}else {
			Cookie[] cookies = request.getCookies();
			for(Cookie c : cookies) {	//쿠키들 중에서
				if(c.getName().equals("rememberId")) {//name이 id 인것
					c.setValue("");		//빈값을 넣어주거나
					c.setMaxAge(0);		//쿠키 나이 0 세팅
					c.setPath("/");
					log.info("cookie 삭제하기");
					response.addCookie(c); //다시 응답으로 보내주기
					break;
				}
			}
			
			
			log.info("=======================id저장하지 않기 {}",rememberId);
		}
		
		
		log.info("Login 성공시 실행 ");		
		authentication.getPrincipal(); //memberVO
		authentication.getCredentials();
		authentication.getAuthorities();
		response.sendRedirect("/");
		
	}

	
	
	
}
