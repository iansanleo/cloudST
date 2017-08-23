package com.cloudST.controller;

import com.cloudST.model.Usuario;
import com.cloudST.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/user")
	public String usuario(Model model){
		Usuario usuario = new Usuario();
		
		usuario = usuarioRepository.findOne(1);
		
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
