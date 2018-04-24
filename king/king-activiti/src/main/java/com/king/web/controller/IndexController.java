package com.king.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping(value="/")
	public String index(){
		
		return "main";
	}
	
	@RequestMapping("/welcome")
	public String welcome(){
		
		return "welcome";
	}
	
	
}
