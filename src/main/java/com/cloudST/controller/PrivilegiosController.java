package com.cloudST.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrivilegiosController {
	
	@GetMapping("/right")
	public String listRight(Model model){
		
		return "right";
	}

}
