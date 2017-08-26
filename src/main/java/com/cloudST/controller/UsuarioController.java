package com.cloudST.controller;
 
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cloudST.model.Usuario;
import com.cloudST.repository.UsuarioRepository;
 

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	
	@GetMapping("/user")
	public String usuarioProfile(Model model){
		Usuario usuario = new Usuario();
		
		usuario = usuarioRepository.findOne(1);

		model.addAttribute(usuario);
		return "usuario";
	}

	@GetMapping("/userAdd")
	public String usuarioAdd(Model model){
		Usuario usuario = new Usuario();
		
		usuario.setNombre("Christian Sanchez Leon");
		usuario.setPassword("123456");
		usuario.setStatus(true);
		usuario.setUserName("christian");
		usuario.setEmail("christian@12.com");
		Calendar fechaInicio = Calendar.getInstance();
		usuario.setFechaInicio(fechaInicio);
		
		usuarioRepository.save(usuario);
		
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
