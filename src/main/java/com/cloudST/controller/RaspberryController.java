package com.cloudST.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cloudST.service.RaspberryService;

@Controller
public class RaspberryController {

	@Autowired
	RaspberryService raspberryService;
	
	@GetMapping("/devicesList")
	public String listDevices(Model model){
		System.out.println("entra");
		model.addAttribute("devices", raspberryService.listDevicesOn());
		System.out.println("dev pag");
		return "listRasp";
	}
	
	@GetMapping("/deleteDevice")
	public String deleteDevice(Model model, HttpServletRequest request){
		
		raspberryService.delete(Integer.parseInt(request.getParameter("deviceId")));
		model.addAttribute("Msg",""+ request.getParameter("deviceId").toString());
		return "listRasp";
	} 
}
