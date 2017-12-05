package com.cloudST.controller;

import com.cloudST.model.Raspberry;
import com.cloudST.service.RaspberryService;
import com.cloudST.utiles.DateUtils;
import com.cloudST.utiles.SystemInfo;

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

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

@Controller
public class RaspberryController {
	
	@Autowired
	RaspberryService raspberryService;
	
	@PostMapping("/addDevices")
	public String addLocalDevice(Model model){
		raspberryService.scan();
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
		//nuevos
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
        ResponseEntity<Raspberry> response;

		Raspberry raspberry = ownSystemInformation();
        
       response = new ResponseEntity<>(raspberry, HttpStatus.OK);
      
        return response;
    }

    public void signUpDevice(){
		Raspberry info = ownSystemInformation();
		raspberryService.create(info);
	}

	private Raspberry ownSystemInformation() {
		Raspberry raspberry = new Raspberry();
		raspberry.setConexionDate(DateUtils.actualDate());
		raspberry.setIp(getIp());
		raspberry.setMac(getMac());
		raspberry.setStatus(true);
		raspberry.setTotalSize(getTotalSize());
		raspberry.setUseSize(getUsedSize());
		return raspberry;
	}

	private InetAddress getInetIp(){
		try {
			DatagramSocket socket = new DatagramSocket();
			socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			InetAddress ip = socket.getLocalAddress();
			return ip;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
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
		File f = new File(SystemInfo.path());
		
	return  f.getTotalSpace()/10000000.00;
	}
	
	private double getUsedSize(){
		File f = new File(SystemInfo.path());
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return raspberry;
	}

}

