package com.cloudST.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cloudST.dao.UsuarioDaoImpl;
import com.cloudST.model.Usuario;

@Controller
public class UsuarioController {

	private UsuarioDaoImpl usuarioDaoImpl;

	@GetMapping("/user")
	public String usuario(Model model){
		Usuario usuario = new Usuario();
		
		usuario = usuarioDaoImpl.findById(1);
		
		//usuario.setNombre("Pepe");
		
		model.addAttribute("name",usuario.getNombre());
		return "usuario";
	}
	
	
	@PostMapping(value="/userPost")
	//post solo formularios, get para todo
	public String displayUsuarioPage (Model model){
		Usuario usuario = new Usuario();
		
		//usuarioMapper.selectUsuario(0);
		usuario.setNombre("Pepe");
		
		model.addAttribute("name",usuario.getNombre());
		
		return "usuario";
		
	}
	
	
}
