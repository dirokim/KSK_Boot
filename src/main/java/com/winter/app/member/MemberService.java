package com.winter.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService implements UserDetailsService {
		
	//add 검증 메서드
	//비번일치 , id 중복 여부
	@Autowired
	private MemberDAO memberDAO;

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
		return memberDAO.add(memberVO);
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
}
