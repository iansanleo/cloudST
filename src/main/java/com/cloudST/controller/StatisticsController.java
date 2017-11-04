package com.cloudST.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cloudST.model.File;
import com.cloudST.model.User;
import com.cloudST.service.FileService;
import com.cloudST.service.PrivilegeService;
import com.cloudST.service.RaspberryService;
import com.cloudST.service.UserService;

@Controller
public class StatisticsController {
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private RaspberryService raspberryService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PrivilegeService privilegeService;
	
	@GetMapping("/statistics")
	public String mostrarStats(Model model){ 
		
		//espacio utilizado respecto al espacio total %
		model.addAttribute("usedPercent",percUsed());
		
		//usuarios no validos %
		model.addAttribute("percentNoValid",percentNoValid());
		
		//free usuarios %
		model.addAttribute("percentFreeUser",percentFreeUser());
		//paid usuarios %
		model.addAttribute("percentPaidUser",percentPaidUser());
		//admin usuarios %
		model.addAttribute("percentAdminUser",percentAdminUser());
		
		return "statistics";
	}
	
	@GetMapping("/statFiles")
	public String statFiles(Model model){
		//espacio a liberar pendiente
		model.addAttribute("liberate", forLiberate());
		
		//media de Mb por archivo
		model.addAttribute("meanFile",mbFile());
		
		//media de archivos por usuario
		model.addAttribute("meanUser",meanUser());
		
		//numero total de archivos
		model.addAttribute("numFiles",numFiles());
				
		return "statFiles";
	}
	
	@GetMapping("/statUser")
	public String statUser(Model model){
		//num total de usuarios
		model.addAttribute("totalUser",totalUser());
		
		//num usuarios no validados
		model.addAttribute("numUserNoValid",userNoValid());
		
		//media de archivos por usuario
		model.addAttribute("meanUser",meanUser());
		
		//num Usuarios de pago
		model.addAttribute("numPaidUser",countTypeUser(1));
		
		//num Usuarios free
		model.addAttribute("numFreeUser",countTypeUser(0));
		
		return "statUser";
	}
	
	@GetMapping ("/statSys")
	public String statSys(Model model){
		
		//cantidad espacio libre
		model.addAttribute("freeSpace",freeSpace());
		
		return "statSys";
	}
	
	
	private double percentFreeUser(){
		return (double)(countTypeUser(0)*100)/totalUser();
	}
	private double percentPaidUser(){
		return (double)(countTypeUser(1)*100)/totalUser();
	}
	private double percentAdminUser(){
		return (double)(countTypeUser(3)*100)/totalUser();
	}
	
	private Integer countTypeUser(Integer type){
		int count=0;
		ArrayList<User> listUser = userService.listUser();
		for(int i=0;i<listUser.size();i++){
			if(privilegeService.actualType(listUser.get(i).getIdUser()).equals(type)){
				count++;
			}
		}
		return count;
	}
	
	private int totalUser(){
		return userService.listUser().size();
	}
	
	private double percentNoValid(){
		return (double)(userNoValid()*100)/totalUser();
	}
	
	private int userNoValid(){
		int count=0;
		ArrayList<User> userList = userService.listUser();
		for(int i=0;i<userList.size();i++){
			if(!userList.get(i).getValid()){count++;}
		}
		return count;
	}
	
	private double meanUser(){
		return numFiles()/totalUser();
	}
	
	private double forLiberate(){
		double liberate= 0.0;
		
		ArrayList<File> listFile = fileService.filesLiberate();
		for(int i = 0;i<listFile.size();i++){
			liberate =+listFile.get(i).getSize();
		}
		return liberate;
	}
	
	private double freeSpace(){
		return raspberryService.totalSizeRasps()-raspberryService.totalSizeUsed();
	}
	
	private double percUsed() {
		return ((raspberryService.totalSizeUsed()/raspberryService.totalSizeRasps())*100);
	}

	private double mbFile(){
		return totalSizeFiles()/numFiles();
	}
	
	private double totalSizeFiles(){
		double totalSize=0.0;
		ArrayList<File> listFile =fileService.allFiles();
		
		for(int i=0;i<listFile.size();i++){
			if(listFile.get(i).getStatus()){
				totalSize=+listFile.get(i).getSize();
				}
		}
		return totalSize;
	}
	
	private Integer numFiles(){
		return  fileService.allFiles().size();
	}
}
