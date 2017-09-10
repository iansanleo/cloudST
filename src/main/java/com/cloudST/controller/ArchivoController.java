package com.cloudST.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cloudST.model.Archivos;
import com.cloudST.service.ArchivoService;
import com.cloudST.service.TransaccionService;
import com.cloudST.service.exception.ArchivoException;

@Controller
public class ArchivoController {
	
	@Autowired
	private ArchivoService archivoService;
	@Autowired
	private TransaccionService transaccionService;
	
	@GetMapping("/resources")
	public String listResources(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();

		ArrayList<Archivos> listArchivos = archivoService.allUserFiles((Integer)session.getAttribute("idUserSession"));

		model.addAttribute("archivos", listArchivos);
		return "archivo";
	}
	
	@GetMapping("/deleteResource")
	public String deleteFile(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		
		try{
			archivoService.delete(Integer.parseInt(request.getParameter("idArchivo")));
			transaccionService.createDelete(Integer.parseInt(request.getParameter("idArchivo")), (Integer)session.getAttribute("idUserSession"));
			return "redirect:/resources";
		} catch (ArchivoException e) {
			model.addAttribute(e.getMessage());
			return "redirect:/resources";
			}
	}

}

