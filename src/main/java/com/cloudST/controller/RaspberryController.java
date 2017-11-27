package com.cloudST.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
	
	private static String path ="//home//";
	//private static String path ="C:\\";
    /*
       windows 
       new File("d:/yourApplicationName/" + name + "-uploaded")

        linux
    	new File("/home/yourApplicationName/" + name + "-uploaded")
     */
	
	@PostMapping("/addDevices")
	public String addLocalDevice(Model model){
			Raspberry newDevice= new Raspberry();
			Raspberry oldDevice= new Raspberry();
			String response;
		//Supose 192.168.0.X
		//		 192.168.0.1 gateway
		//		 255.255.255.0
		//add others searching ip
			for(int i=1;i<254;i++){
				try {
					response=sendGET("192.168.0."+i+":8080/identify");
					if(response==null){continue;}
					
					newDevice=createRaspJson(response);
					oldDevice=raspberryService.findByMac(newDevice.getMac());
					
					if(oldDevice==null){
						raspberryService.create(newDevice);
						continue;
					}
					if(!oldDevice.getStatus()){
						raspberryService.create(newDevice);
						continue;
					}
					if(!oldDevice.getIp().equals(newDevice.getIp())){
						raspberryService.delete(oldDevice.getIdRaspberry());
						raspberryService.create(newDevice);
						continue;
						}
					
				} catch (IOException e) {
					continue;
				}
				
			}
		
		return "redirect:/devicesList";
	}
	
	@PostMapping("/addDeviceManually")
	public String addManualDevice(Model model, HttpServletRequest request){
		String ip = request.getParameter("ip").toString();
		String port = request.getParameter("port").toString();
		String response ="";
		
		try {
			response=sendGET("http://"+ip+":"+port+"/identify");
			if(response!=null){
				raspberryService.create(createRaspJson(response));
			}else {
				model.addAttribute("Msg","We were unable to connect, review the data enteredor the remote device configuration, please");
			}
		} catch (IOException e) {
			model.addAttribute("Msg","We were unable to connect, review the data enteredor the remote device configuration, please");
		}
		
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
	
	private String sendGET(String url) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return inputLine;
		} 
		System.out.println("GET request not worked");
		return null;
	}
	
	private Raspberry createRaspJson(String response){
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		Raspberry raspberry = new Raspberry();
		
		try {
			jsonObject = (JSONObject) parser.parse(response);
		} catch (ParseException e1) {
	
			e1.printStackTrace();
		}
		try {
			raspberry.setIp((String) jsonObject.get("ip"));
			raspberry.setMac((String) jsonObject.get("mac"));
			raspberry.setTotalSize((double)jsonObject.get("totalSize"));
			raspberry.setUseSize((double) jsonObject.get("useSize"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return raspberry;
	}

}

