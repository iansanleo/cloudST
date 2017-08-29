package com.cloudST.controller;
 
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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


	@PostMapping("/login")
	public String login(Model model, HttpServletRequest request){
		
		String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        
		Iterable<Usuario> listUsuario = usuarioRepository.findAll(); 
		
		for (Iterator<Usuario> i = listUsuario.iterator(); i.hasNext();) {
		    Usuario usuario = i.next();
		    
		    if(usuario.getUsername().equals(userName)&& usuario.getPassword().equals(password)){
		    		
		    	session.setAttribute("id", usuario.getIdUsusario());
		    	return "welcome";
		    }
		}
		model.addAttribute("Msg", "Password or invalid entered user");
		return "index";
	}
	
	@GetMapping ("/logout")
	public String logOut(Model model, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		session.removeAttribute("id");
		return "index";
	}
	
	
	@GetMapping("/user")
	public String usuarioProfile(Model model, HttpServletRequest request){
		Usuario usuario = new Usuario();
		
		HttpSession session = request.getSession();
		usuario = usuarioRepository.findOne((Integer) session.getAttribute("id"));
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
	
}
