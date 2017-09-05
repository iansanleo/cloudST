package com.cloudST.controller;
 
import java.sql.Timestamp;
import java.util.Date;

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
		
		HttpSession session = request.getSession();
		String userName = request.getParameter("userName").toString();
        String password = request.getParameter("password").toString();

        Usuario usuario = usuarioRepository.findByUser(userName);
	
        if(usuario.getUsername()==null){
        	model.addAttribute("Msg","User does not exist, create one.");
        	return "index";
        }
        
		if(!usuario.getPassword().equals(password)){
			model.addAttribute("Msg", "Password or invalid entered Username");
			return "index";
		}
		
		session.setAttribute("idUser", usuario.getIdUsusario());
		
		Privilegios privilegios = privilegiosRepository.findByIdUser(usuario.getIdUsusario());
    	
    	session.setAttribute("permisos", privilegios.getTipo());
    	
    	return "welcome";
	}
	
	@GetMapping ("/logout")
	public String logOut(Model model, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		session.removeAttribute("idUser");
		session.removeAttribute("permisos");
		return "index";
	}
	
	@GetMapping("/user")
	public String userProfile(Model model, HttpServletRequest request){
		Usuario usuario = new Usuario();
		
		HttpSession session = request.getSession();
		usuario = usuarioRepository.findOne((Integer) session.getAttribute("idUser"));
		model.addAttribute("usuario",usuario);
		return "usuario";
	}
	
	@GetMapping("/editUser")
	public String editProfile(Model model,  HttpServletRequest request){
		HttpSession session = request.getSession();
		Usuario usuario = usuarioRepository.findOne((Integer) session.getAttribute("idUser"));
		
		model.addAttribute("usuario", usuario);
		return "editUsuario";
	}
	
	@PostMapping("/userEdit")
	public String newValueUser(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		Usuario usuario = usuarioRepository.findOne((Integer) session.getAttribute("idUser"));
		
		String nombre = request.getParameter("name").toString();
        String email = request.getParameter("email").toString();
        String password = request.getParameter("password").toString();
        String password2 = request.getParameter("password2").toString();
        
        System.out.println(nombre);
        System.out.println(usuario.getNombre());
        System.out.println(email);
        System.out.println(usuario.getEmail());
        System.out.println(password);
        System.out.println(password2);
        System.out.println(usuario.getPassword());
        
        if(nombre != usuario.getNombre()){
        	usuario.setNombre(nombre);
        }
        if(email != usuario.getEmail()){
        	usuario.setEmail(email);
        	usuario.setValido(false);
        }
        
       if(password!=""){
    	   if(!password.equals(password2)){
    		   model.addAttribute("Msg","Passwords do not match");
    		   return "redirect:/editUser";
    	   }
    	   
    	   usuario.setPassword(password);
    	   
       }
       usuarioRepository.save(usuario);
	   
	   model.addAttribute("Msg","User has been successfully modified");
	   return "redirect:/user" ;
	}
	
	
	@GetMapping("/newUser")
	public String newUser(Model model){
		return "addUsuario";
	}
	
	@PostMapping("/userAdd")
	public String userAdd(Model model, HttpServletRequest request){
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
		
		if(usuarioRepository.findByUser(username)!=null){
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
		
		Privilegios privilegio = new Privilegios(fechaInicio, 0, true, usuarioRepository.findByUser(username).getIdUsusario());
		privilegiosRepository.save(privilegio);
		
		model.addAttribute("Msg", "User successfully added");
		
		if(session.getAttribute("idUser")==null){return "index";}
		
		return "welcome";
	}
	
}
