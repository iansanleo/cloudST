package com.cloudST.controller;

import com.cloudST.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UsuarioController {


	@GetMapping("/user")
	public String usuario(Model model){
		Usuario usuario = new Usuario();
		usuario.setNombre("Pepe");
		model.addAttribute("name",usuario.getNombre());
		return "usuario";
	}
	
	//post solo formularios, get para todo
	@RequestMapping(value="/user", method = RequestMethod.POST)
	public String displayUsuarioPage (Model model){
		Usuario usuario = new Usuario();
		
		//usuarioMapper.selectUsuario(0);
		usuario.setNombre("Pepe");
		
		model.addAttribute("name",usuario.getNombre());
		
		return "usuario";
		
	}
	
	
}
