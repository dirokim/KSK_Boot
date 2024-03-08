package com.winter.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("add")
	public void add (@ModelAttribute MemberVO memberVO) throws Exception {
		
	}
	
	@PostMapping("add")
	public String add (@Valid MemberVO memberVO,BindingResult bindingResult,Model model) throws Exception {
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
