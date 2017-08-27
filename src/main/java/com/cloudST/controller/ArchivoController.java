package com.cloudST.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cloudST.model.Archivos;
import com.cloudST.repository.ArchivoRepository;

@Controller
public class ArchivoController {
	
	@Autowired
	private ArchivoRepository archivoRepository;
	
	@GetMapping("/resources")
	public String listResources(Model model, HttpServletRequest request){
		ArrayList<Archivos> archivos = new ArrayList<Archivos>();
		HttpSession session = request.getSession();
		
		//Archivos =  (ArrayList<Archivos>) archivoRepository.findAll();
		
	    for (Archivos archivo : archivoRepository.findAll()) {
	    	if(archivo.getIdUsuario()== session.getAttribute("id"))
	    		archivos.add(archivo);
	    	System.out.println("id :"+ session.getAttribute("id") );
	    System.out.println(archivo.getIdUsuario());
	    System.out.println("tamnayo:"+archivos.size());
	    }
		
	    model.addAllAttributes(archivos);
		return "archivo";
	}

}
