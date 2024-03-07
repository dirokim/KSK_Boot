package com.winter.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.winter.app.transfer.Bus;
import com.winter.app.transfer.Subway;

import lombok.extern.slf4j.Slf4j;
@Controller
@Slf4j
public class TestController {
	@Autowired
	private Bus bus;
	@Autowired
	private Subway subway;
	
	@GetMapping("/")
	public String test () {
		//trace , debug, info ,warn ,error	
		subway.getSubway(10);
		bus.getBus();
		
		return "index";
	}
	
	

}
