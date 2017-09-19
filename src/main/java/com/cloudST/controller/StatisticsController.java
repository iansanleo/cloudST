package com.cloudST.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cloudST.model.Archivos;
import com.cloudST.service.ArchivoService;
import com.cloudST.service.RaspberryService;
import com.cloudST.service.UsuarioService;

@Controller
public class StatisticsController {
	
	@Autowired
	private ArchivoService archivoService;
	
	@Autowired
	private RaspberryService raspberryService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/statistics")
	public String mostrarStats(Model model){ 
		
		//espacio utilizado respecto al espacio total %
		model.addAttribute("usedPercent",porcUsado());
		
		//cantidad espacio libre
		model.addAttribute("libre",espacioLibre());
		
		//numero total de archivos
		model.addAttribute("numArch",numArchivos());
		
		//media de archivos por usuario
		model.addAttribute("mediaUsuario",mediaUsuario());
		
		//media de Mb por archivo
		model.addAttribute("mediaArchivo",mbArchivo());
		
		//espacio a liberar pendiente
		model.addAttribute("liberar", pendienteLiberar());
		
		
		return "estadisticas";
	}
	
	private double mediaUsuario(){
		
		return numArchivos()/usuarioService.listaUsuario().size();
	}
	
	private double pendienteLiberar(){
		double liberar= 0.0;
		
		ArrayList<Archivos> listArchivos = archivoService.archivosLiberar();
		for(int i = 0;i<listArchivos.size();i++){
			liberar =+listArchivos.get(i).getTamanyo();
		}

		return liberar;
	}
	
	private double espacioLibre(){
		
		return raspberryService.espacioTotalRasps()-raspberryService.espacioTotalUso();
	}
	
	private double porcUsado() {

		return (raspberryService.espacioTotalUso()/raspberryService.espacioTotalRasps())*100;
	}

	private double mbArchivo(){
		double tamanyoTotalArchivos=totalEspacioArchivos()/numArchivos();
		
		return tamanyoTotalArchivos;
		
	}
	
	private double totalEspacioArchivos(){
		double tamanyoTotal=0.0;
		ArrayList<Archivos> listArchivos = archivoService.allFiles();
		
		for(int i=0;i<listArchivos.size();i++){
			if(listArchivos.get(i).getStatus()){
				tamanyoTotal=+listArchivos.get(i).getTamanyo();
				}
		}
		return tamanyoTotal;
	}
	
	private Integer numArchivos(){
		ArrayList<Archivos> listArchivos = archivoService.allFiles();
		Integer numArchivos = listArchivos.size();
		return  numArchivos;
	}
}
