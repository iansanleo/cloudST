package com.cloudST.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cloudST.model.Archivos;
import com.cloudST.model.Transaccion;
import com.cloudST.repository.ArchivoRepository;
import com.cloudST.repository.TransaccionRespository;

@Controller
public class ArchivoController {
	
	@Autowired
	private ArchivoRepository archivoRepository;
	@Autowired
	private TransaccionRespository transaccionRepository;
	
	@GetMapping("/resources")
	public String listResources(Model model, HttpServletRequest request) {
		ArrayList<Archivos> archivos = new ArrayList<Archivos>();
		HttpSession session = request.getSession();

		for (Archivos archivo : archivoRepository.findAll()) {
			
			if (archivo.getIdUsuario() == session.getAttribute("idUser")){
				
				if(archivo.getStatus()){archivos.add(archivo);}
			}
		}

		model.addAttribute("archivos", archivos);
		return "archivo";
	}
	@GetMapping("/deleteResource")
	public String deleteFile(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		
		Archivos archivo = archivoRepository.findOne(Integer.parseInt(request.getParameter("idArchivo")));
		archivo.setStatus(false);
		
		archivoRepository.save(archivo);
		
		Transaccion transaccion = new Transaccion();
		
		Date fechaInicio = new java.util.Date(); //fecha actual
		Timestamp sqlTimestamp = new Timestamp(fechaInicio.getTime());//en milisegundos
		fechaInicio = new Date(sqlTimestamp.getTime());
		
        transaccion.setFecha(fechaInicio);
		transaccion.setIdArchivo(Integer.parseInt(request.getParameter("idArchivo")));
		transaccion.setidUsuario((Integer)session.getAttribute("idUser"));
		transaccion.setTipo("delete");
		
		transaccionRepository.save(transaccion);
		
		return "redirect:/resources";
	}
	
}
