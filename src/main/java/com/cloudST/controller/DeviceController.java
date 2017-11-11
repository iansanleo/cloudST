package com.cloudST.controller;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeviceController {

	@GetMapping("/Mac")
	public String getMac(Model model){
		InetAddress ip;
		try {

			ip = InetAddress.getLocalHost();
			System.out.println("Current IP address : " + ip.getHostAddress());

			NetworkInterface network = NetworkInterface.getByInetAddress(ip);

			byte[] mac = network.getHardwareAddress();

			System.out.print("Current MAC address : ");

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			}
			System.out.println(sb.toString());
			
			 File f = new File("C:\\");
			 System.out.println("Printing the total space");
			 System.out.println(f.getTotalSpace()/1000000.00 +" Megabytes");
			 System.out.println(f.getTotalSpace()/1000000000.00 +" Gigabytes");
			 
			 System.out.println("Printing the free space");
			 System.out.println(f.getFreeSpace()/1000000.00 +" Megabytes");
			 System.out.println(f.getFreeSpace()/1000000000.00 +" Gigabytes");
			
		

		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (SocketException e){

			e.printStackTrace();

		}

		return "";
	}
}
