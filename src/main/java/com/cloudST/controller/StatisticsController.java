package com.cloudST.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cloudST.model.File;
import com.cloudST.service.FileService;
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
	
	@GetMapping("/statistics")
	public String mostrarStats(Model model){ 
		
		//espacio utilizado respecto al espacio total %
		model.addAttribute("usedPercent",percUsed());
		
		//cantidad espacio libre
		model.addAttribute("freeSpace",freeSpace());
		
		//numero total de archivos
		model.addAttribute("numFiles",numFiles());
		
		//media de archivos por usuario
		model.addAttribute("meanUser",meanUser());
		
		//media de Mb por archivo
		model.addAttribute("meanFile",mbFile());
		
		//espacio a liberar pendiente
		model.addAttribute("liberate", forLiberate());
		
		
		return "statistics";
	}
	
	private double meanUser(){
		return numFiles()/userService.listUser().size();
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
		return (raspberryService.totalSizeUsed()/raspberryService.totalSizeRasps())*100;
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
