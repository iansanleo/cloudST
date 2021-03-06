package com.cloudST.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

	@GetMapping("/")
	public String getIndex(Model model){
		return "index";
	}
	
	@GetMapping("/welcome")
	public String getWelcome(Model model){
		return "welcome";
	}
	
	@GetMapping("/noPermit")
	public String withOutSession(Model model){
		return "index";
	}
	
	@GetMapping("/help")
	public String help(Model model){
		return "help";
		
	}
}
