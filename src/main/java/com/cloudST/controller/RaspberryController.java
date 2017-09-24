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
		model.addAttribute("devices", raspberryService.listDevicesOn());
		return "listRasp";
	}
	
	@GetMapping("/deleteDevice")
	public String deleteDevice(Model model, HttpServletRequest request){
		raspberryService.delete(Integer.parseInt(request.getParameter("deviceId")));
		model.addAttribute("Msg","Device: "+ raspberryService.findById(Integer.parseInt(request.getParameter("deviceId"))).getIp()+" deleted.");
		return "listRasp";
	} 
}
