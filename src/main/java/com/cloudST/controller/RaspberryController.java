package com.cloudST.controller;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cloudST.model.Raspberry;
import com.cloudST.service.RaspberryService;

@Controller
public class RaspberryController {
	
	@Autowired
	RaspberryService raspberryService;
	
	private static String path ="C:\\";
    /*
       windows 
       new File("d:/yourApplicationName/" + name + "-uploaded")

        linux
    	new File("/home/yourApplicationName/" + name + "-uploaded")
     */
	
	@PostMapping("/addDevices")
	public String addLocalDevice(Model model){
		Raspberry device = raspberryService.findByMac(getMac());
		if(device==null){
			 File f = new File(path);
			 //GB
			raspberryService.create(getIp(), getMac(), f.getTotalSpace()/1000000000.00 , f.getFreeSpace()/1000000000.00);
		}else{
			device.setStatus(false);
			raspberryService.update(device.getIdRaspberry(),device.getIp(),device.getMac(),device.getTotalSize(),
					device.getUseSize(),device.getConexionDate(),false);
			
		}
		//add others
		
		return "redirect:/devicesList";
	}
	
	
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
	
	
	private InetAddress getInetIp(){
		try {
			InetAddress ip = InetAddress.getLocalHost();
			return ip;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}
	private String getIp(){
		return getInetIp().getHostAddress();
	}
	
	private String getMac(){
		try {
			NetworkInterface network = NetworkInterface.getByInetAddress(getInetIp());

			byte[] mac = network.getHardwareAddress();
			
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			}
			return sb.toString();
		} catch (SocketException e) {
	
			e.printStackTrace();
		}
		
		return null;
	}
}
