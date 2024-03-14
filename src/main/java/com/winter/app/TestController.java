package com.winter.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.winter.app.lambda.TestInterface;
import com.winter.app.member.MemberVO;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Supplier;
import lombok.extern.slf4j.Slf4j;
@Controller
@Slf4j
public class TestController {

	
	@GetMapping("/")
	public String test () throws Exception {
		//람다는 자바스크립트의 에러 펑션이랑 같다 function(){} - > ()->{}
		//java ()->{}
		TestInterface ti = (int a , int b )->a+b;
		System.out.println(ti.t1(3, 2));
		Supplier<MemberVO> s = ()-> new MemberVO();
		MemberVO memberVO = s.get();
		
		System.out.println(ti.t1(6, 12));
		
		
		return "index";
	}
	
	@GetMapping("/expired")
	public String expried(Model model) {
		model.addAttribute("result","로그아웃");
		model.addAttribute("path","/");
		return "commons/result";
	}
	

}
