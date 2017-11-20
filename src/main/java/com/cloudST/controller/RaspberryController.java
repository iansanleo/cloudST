package com.cloudST.controller;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cloudST.model.Raspberry;
import com.cloudST.service.RaspberryService;
import com.cloudST.utiles.DateUtils;

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
		
		if(device!=null && device.getStatus() && device.getIp().equals(getIp())){
		return "redirect:/devicesList";
		}
		if(device!=null && device.getStatus()){
			device.setStatus(false);
			raspberryService.delete(device.getIdRaspberry());
		}
			raspberryService.create(getIp(), getMac(), getTotalSize() ,getUsedSize());
		
		
		//add others searching ip
		
		return "redirect:/devicesList";
	}
	@PostMapping("/addDeviceManually")
	public String addManualDevice(Model model, HttpServletRequest request){
		String ip = request.getParameter("ip").toString();
		String port = request.getParameter("port").toString();
		
		model.addAttribute("Msg","Remote Device added succefully!");
		return "listRasp";
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
	
	//Json
	@RequestMapping(value = "/identify", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Raspberry> getRaspberry() {
        Raspberry raspberry = new Raspberry();
        ResponseEntity<Raspberry> response;
       
        raspberry.setConexionDate(DateUtils.actualDate());
        raspberry.setIp(getIp());
        raspberry.setMac(getMac());
        raspberry.setStatus(true);
        raspberry.setTotalSize(getTotalSize());
        raspberry.setUseSize(getUsedSize());
        
       response = new ResponseEntity<>(raspberry, HttpStatus.OK);
      
        return response;
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
	
	private double getTotalSize(){
		File f = new File(path);	
	return  f.getTotalSpace()/10000000.00;
	}
	
	private double getUsedSize(){
		File f = new File(path);
	return (getTotalSize()-(f.getFreeSpace()/10000000.00));
	}
}
