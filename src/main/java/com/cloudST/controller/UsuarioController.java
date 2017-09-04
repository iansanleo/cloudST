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

import com.cloudST.model.Privilegios;
import com.cloudST.model.Usuario;
import com.cloudST.repository.PrivilegiosRepository;
import com.cloudST.repository.UsuarioRepository;
 

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PrivilegiosRepository privilegiosRepository;


	@PostMapping("/login")
	public String login(Model model, HttpServletRequest request){
		
		String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        
		Iterable<Usuario> listUsuario = usuarioRepository.findAll(); 
		
		for (Iterator<Usuario> i = listUsuario.iterator(); i.hasNext();) {
		    Usuario usuario = i.next();
		    
		    if(usuario.getUsername().equals(userName)&& usuario.getPassword().equals(password)){
		    		
		    	session.setAttribute("idUser", usuario.getIdUsusario());
		    	
		    	//
		    	//privilegiosRepository
		    	//model.addAttribute("perm",true);
		    	
		    	return "welcome";
		    }
		}
		model.addAttribute("Msg", "Password or invalid entered Username");
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
	
	@GetMapping("/newUser")
	public String newUsuario(Model model){
		return "addUsuario";
	}
	
	@PostMapping("/userAdd")
	public String usuarioAdd(Model model, HttpServletRequest request){
		Usuario usuario = new Usuario();
		
		HttpSession session = request.getSession();
		
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String username = request.getParameter("userName");
		String email = request.getParameter("email");
		
		if(!password.equals(password2)){
			model.addAttribute("Msg", "Passwords doesn't mach");
			return "addUsuario";
		}
		
		if(usuarioRepository.findByEmail(email)!=null){
			model.addAttribute("Msg", "The email you entered is currently in use");
			return "addUsuario";
		}
		
		if(usuarioRepository.findByUsername(username)!=null){
			model.addAttribute("Msg", "The username you entered is currently in use");
			return "addUsuario";
		}
		
		usuario.setNombre(request.getParameter("name").toLowerCase());
		usuario.setPassword(password);
		usuario.setUsername(username);
		usuario.setEmail(email);
		
		usuario.setStatus(true);
		usuario.setValido(false);
		
		Date fechaInicio = new java.util.Date(); //fecha actual
		Timestamp sqlTimestamp = new Timestamp(fechaInicio.getTime());//en milisegundos
		fechaInicio = new Date(sqlTimestamp.getTime());
		usuario.setFechaInicio(fechaInicio);
		usuarioRepository.save(usuario);
		
		Privilegios privilegio = new Privilegios(fechaInicio, 0, true, usuarioRepository.findByUsername(username).getIdUsusario());
		privilegiosRepository.save(privilegio);
		
		model.addAttribute("Msg", "User successfully added");
		
		if(session.getAttribute("id")==null){return "index";}
		
		return "welcome";
	}
	
}
