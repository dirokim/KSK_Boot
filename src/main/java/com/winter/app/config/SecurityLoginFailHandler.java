package com.winter.app.config;

import java.io.IOException;
import java.net.URLEncoder;

import org.eclipse.tags.shaded.org.apache.bcel.generic.NEW;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.winter.app.member.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class SecurityLoginFailHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		log.info("로그인 실패 원인 ==>>{}",exception);  //로그인 실패시 익셉션 일으키고 그걸 확인 가능
		String message ="로그인 실패";
		if(exception instanceof BadCredentialsException) {
			message="비밀번호 확인";
		};
		if(exception instanceof InternalAuthenticationServiceException) {
			message="아이디가 불일치";
		}
		if(exception instanceof AccountExpiredException) {
			message="계정 유효기간 만료";
		}
		if(exception instanceof LockedException) {
			message="계정이 잠김";
		}
		if(exception instanceof CredentialsExpiredException) {
			message="비번의 유효기간이 종료";
		}
		if(exception instanceof DisabledException) {
			message="휴면 계정입니다";
			
		}
		message= URLEncoder.encode(message,"UTF-8");
		response.sendRedirect("/member/login?message="+message);
		
		//forward
//		request.setAttribute("message", message);
//		request.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(request, response);
		
		
		
	}

	
	
}
