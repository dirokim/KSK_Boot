package com.winter.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class TestController {
	
	@GetMapping("/")
	public String test () {
		
		return "index";
	}
	
	
	@GetMapping("")
	public void testTest() {
		
	}
}
