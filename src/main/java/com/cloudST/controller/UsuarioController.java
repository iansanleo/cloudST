package com.cloudST.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cloudST.mapper.UsuarioMapper;
import com.cloudST.model.Usuario;

@Controller
public class UsuarioController {

	private
	UsuarioMapper usuarioMapper;
	
	
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
