package com.cloudST.controller;
 
import java.sql.Timestamp;
import java.util.Date;

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
		
		usuario = usuarioRepository.findOne(2);

		model.addAttribute("usuario",usuario);
		return "usuario";
	}

	@GetMapping("/userAdd")
	public String usuarioAdd(Model model){
		Usuario usuario = new Usuario();
		
		usuario.setNombre("Christian Sanchez Leon");
		usuario.setPassword("123456");
		usuario.setStatus(true);
		usuario.setUsername("christian");
		usuario.setEmail("christian@12.com");
		
		Date fechaInicio = new java.util.Date(); //fecha actual
		Timestamp sqlTimestamp = new Timestamp(fechaInicio.getTime());//en milisegundos
		fechaInicio = new Date(sqlTimestamp.getTime());
		
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
