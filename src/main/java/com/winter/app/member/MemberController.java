package com.winter.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.member.groups.MemberJoinGroup;
import com.winter.app.member.groups.MemberUpdateGroup;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Member;
import java.util.Enumeration;

@Slf4j
@Controller
@RequestMapping("/member/*")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	
	 
	@GetMapping("page")
	public void page (HttpSession session) throws Exception {
		//속성명이 무엇인가 ?   
		Enumeration<String> en = session.getAttributeNames();
		   while(en.hasMoreElements()) {
			   en.nextElement();
		   }
		   Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		   SecurityContextImpl contectImpl = (SecurityContextImpl)obj;
		   String name = contectImpl.getAuthentication().getName();
		   MemberVO memberVO = (MemberVO)contectImpl.getAuthentication().getPrincipal();
		   
		   
		   SecurityContext sc = SecurityContextHolder.getContext();
		   sc.getAuthentication().getName();
		   sc.getAuthentication().getPrincipal();
	}
	
	@GetMapping("login")
	public String login (@ModelAttribute MemberVO memberVO,HttpSession session)throws Exception {
		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		if(obj==null) {
			return "member/login";
		}
		SecurityContextImpl contextImpl = (SecurityContextImpl)obj;
		String user =  contextImpl.getAuthentication().getPrincipal().toString();
		if(user.equals("anonymousUser")) {
			return "member/login";
		}	
		return"redirect:/";
	}
	
	
	@PostMapping("update")
	public void update (@Validated(MemberUpdateGroup.class)MemberVO memberVO)throws Exception {
		
	}
	
	@GetMapping("update")
	public void update (Model model,MemberVO memberVO)throws Exception {
		model.addAttribute("memberVO",memberVO);
	}
	
	@GetMapping("add")
	public void add (@ModelAttribute MemberVO memberVO) throws Exception {
	}
	
	@PostMapping("add")
	public String add (@Validated(MemberJoinGroup.class) MemberVO memberVO,BindingResult bindingResult,Model model) throws Exception {
		boolean check = memberService.checkMember(memberVO, bindingResult);
		if(check) {
			return "member/add";	
		}
		int result = memberService.add(memberVO);
		model.addAttribute("result","member.add.result");
		model.addAttribute("path","/");
		return "commons/result";
	}
}
