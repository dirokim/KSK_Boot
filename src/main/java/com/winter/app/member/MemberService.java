package com.winter.app.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import ch.qos.logback.core.net.server.Client;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class MemberService extends DefaultOAuth2UserService implements UserDetailsService  {
		
	//add 검증 메서드
	//비번일치 , id 중복 여부
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	
	
	
	//UserDetailsService
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		try {
			memberVO =  memberDAO.getDetail(memberVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberVO;
	}

	public int add (MemberVO memberVO)throws Exception{
		//평문을 암호화 하는 작업
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		int result = memberDAO.add(memberVO);
		//회원의 Role 정보를 저장
		memberDAO.addMemberRole(memberVO);
		return result;
	}
	
	public boolean checkMember(MemberVO memberVO,BindingResult bindingResult) throws Exception{
		boolean check = false;
		//check 가 true 라면 에러가 잇따
		//check 가 false 라면 에러가 없다.
		//annotation 검증 결과
		check =  bindingResult.hasErrors();
		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())) {
			check = true;
			bindingResult.rejectValue("passwordCheck", "MemberVO.password.equals");
			}
		   MemberVO result = memberDAO.getDetail(memberVO);
	    if(result != null) {
		    check=true;
		    bindingResult.rejectValue("username","MemberVO.username.equals");
		  }
		return check;
	}
	
	//DefaultOAuth2UserService
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		log.info("kakao ===================={}",userRequest);
		ClientRegistration c = userRequest.getClientRegistration();
		log.info("================id:{}",c.getClientId()); 
		log.info("================name:{}",c.getClientName());
		OAuth2User user = super.loadUser(userRequest);
		log.info("================name :{}",user.getName());
		log.info("================user :{}",user.getAuthorities());
		log.info("=====properties : {}",user.getAttribute("properties").toString());
		if(c.getClientName().equalsIgnoreCase("Kakao")) {
			user = this.kakao(user);
		}
		return user;
	}
	
	private OAuth2User kakao(OAuth2User oAuth2User) {
		Map<String,Object> map = oAuth2User.getAttribute("properties");
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(oAuth2User.getName());
		memberVO.setName(map.get("nickname").toString());
		memberVO.setAttributes(oAuth2User.getAttributes());
		List<RoleVO> lsit = new ArrayList<>();
		RoleVO roleVO = new RoleVO();
		roleVO.setRolename("ROLE_MEMBER");
		memberVO.setRoleVOs(lsit);
		return memberVO;
	}
	
}
