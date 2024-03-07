package com.winter.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



import lombok.extern.slf4j.Slf4j;
@Controller
@Slf4j
public class TestController {

	
	@GetMapping("/")
	public String test () {
		//trace , debug, info ,warn ,error	

		return "index";
	}
	
	

}
